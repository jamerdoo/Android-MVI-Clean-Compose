package com.dlebal.androidtechnicaltest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import com.dlebal.androidtechnicaltest.domain.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterOfDayEntity(
    @PrimaryKey val id: String,
    val name: String,
    val species: String,
    val image: String,
    val selectedDay: String,
    val gender: String,
    val status: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id= id, 
                name = name, 
                species = species, 
                image = image,
                gender = gender,
                status = status),
            selectedDay = selectedDay
        )
    }
}

