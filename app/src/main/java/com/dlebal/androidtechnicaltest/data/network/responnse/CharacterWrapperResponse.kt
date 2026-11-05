package com.dlebal.androidtechnicaltest.data.network.responnse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterWrapperResponse (
    val info: InfoResponse,
    @SerialName("results")
    val results: List<CharacterResponse>
)