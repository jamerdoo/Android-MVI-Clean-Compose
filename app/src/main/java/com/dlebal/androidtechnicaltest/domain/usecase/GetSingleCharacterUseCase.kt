package com.dlebal.androidtechnicaltest.domain.usecase

import com.dlebal.androidtechnicaltest.domain.Repository
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import javax.inject.Inject

class GetSingleCharacterUseCase @Inject constructor(
    val repository: Repository
) {

    suspend operator fun invoke(id: String?) : CharacterModel {
        val result = id?.let { characterId ->
            repository.getSingleCharacter(characterId.toInt())
        }?:run {
            getRandomCharacter()
        }
        return result
    }

    private suspend fun getRandomCharacter() : CharacterModel {
        val randomCharacter = (1..826).random()
        return repository.getSingleCharacter(randomCharacter)
    }

}