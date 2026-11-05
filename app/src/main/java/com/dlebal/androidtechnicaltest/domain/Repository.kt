package com.dlebal.androidtechnicaltest.domain

import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getSingleCharacter(id: Int) : CharacterModel

    fun getAllCharacter() : Flow<PagingData<CharacterModel>>

}