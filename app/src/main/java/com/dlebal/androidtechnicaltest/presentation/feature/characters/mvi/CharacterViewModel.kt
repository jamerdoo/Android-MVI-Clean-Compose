package com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.dlebal.androidtechnicaltest.domain.usecase.GetAllCharactersUseCase
import com.dlebal.androidtechnicaltest.domain.usecase.GetSingleCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getSingleCharacterUseCase: GetSingleCharacterUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state.asStateFlow()

    private val _intent = MutableSharedFlow<CharacterIntent>()

    init {
        viewModelScope.launch { handleIntent() }
        sendIntent(CharacterIntent.LoadAll)
    }

    private suspend fun handleIntent() {
        _intent.collect { intent ->
            when (intent) {
                is CharacterIntent.LoadCharacter -> loadCharacter()
                CharacterIntent.LoadAll -> {
                    loadAllCharacter()
                }

                CharacterIntent.ReloadList -> {}
            }
        }
    }

    private fun loadAllCharacter(){
        viewModelScope.launch {
            val characters = getAllCharacter().cachedIn(viewModelScope)
            _state.update {
                it.copy(
                    characterList = UiResult.Success(characters)
                )
            }
        }
    }

    private fun getAllCharacter() : Flow<PagingData<CharacterModel>> {
        _state.update { it.copy(characterList = UiResult.Loading) }
        return getAllCharactersUseCase.invoke()
    }

    private suspend fun loadCharacter(characterId: String? = "") : CharacterModel {
        _state.update { it.copy(loadCharacter = UiResult.Loading) }
        return getSingleCharacterUseCase.invoke(characterId)
    }


    fun sendIntent(intent: CharacterIntent) {
        viewModelScope.launch { _intent.emit(intent) }
    }
}