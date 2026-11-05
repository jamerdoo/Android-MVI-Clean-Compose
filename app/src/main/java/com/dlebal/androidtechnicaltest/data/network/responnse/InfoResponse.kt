package com.dlebal.androidtechnicaltest.data.network.responnse

import kotlinx.serialization.Serializable

@Serializable
class InfoResponse (
    val pages: Int,
    val next: String?,
    val prev: String?,
    val count: Int
)
