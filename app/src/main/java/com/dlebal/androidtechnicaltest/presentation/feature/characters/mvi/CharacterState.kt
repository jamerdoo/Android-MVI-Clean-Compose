package com.dlebal.androidtechnicaltest.presentation.feature.characters.mvi

import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

data class CharacterState (
    val loadCharacter: UiResult<CharacterModel> = UiResult.Loading,
    val characterList: UiResult<Flow<PagingData<CharacterModel>>> = UiResult.Loading
)