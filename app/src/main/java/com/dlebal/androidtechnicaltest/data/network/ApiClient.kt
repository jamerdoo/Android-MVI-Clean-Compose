package com.dlebal.androidtechnicaltest.data.network

import com.dlebal.androidtechnicaltest.data.network.responnse.CharacterResponse
import com.dlebal.androidtechnicaltest.data.network.responnse.CharacterWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiClient {

    @GET("/api/character/{id}")
    suspend fun getSingleCharacter(@Path("id")id: Int): CharacterResponse

    @GET("/api/character/")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterWrapperResponse

}