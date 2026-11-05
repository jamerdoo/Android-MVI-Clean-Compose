package com.dlebal.androidtechnicaltest.domain.model

import com.dlebal.androidtechnicaltest.data.database.entity.CharacterOfDayEntity


data class CharacterOfTheDayModel (
    val characterModel: CharacterModel,
    val selectedDay: String
) {
    fun toEntity() : CharacterOfDayEntity {
        return CharacterOfDayEntity(
            id = characterModel.id,
            name = characterModel.name,
            image = characterModel.image,
            species = characterModel.species,
            gender = characterModel.gender,
            status = characterModel.status,
            selectedDay = selectedDay
        )
    }
}