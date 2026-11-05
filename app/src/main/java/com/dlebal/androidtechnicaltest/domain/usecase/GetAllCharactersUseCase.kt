package com.dlebal.androidtechnicaltest.domain.usecase

import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.domain.Repository
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllCharactersUseCase @Inject constructor(
    val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacter()
    }
}