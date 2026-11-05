package com.dlebal.androidtechnicaltest.data.network.responnse

import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse (
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("status") val status: String,
    @SerialName("species") val species: String,
    @SerialName("gender") val gender: String,
    val image: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id.toString(),
            name = name,
            image = image,
            species = species,
            status = status,
            gender = gender
        )
    }
}