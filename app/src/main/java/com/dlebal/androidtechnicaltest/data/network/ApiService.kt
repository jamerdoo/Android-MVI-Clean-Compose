package com.dlebal.androidtechnicaltest.data.network

import com.dlebal.androidtechnicaltest.data.network.responnse.CharacterResponse
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.core.common.paging.CharacterPagingSource
import javax.inject.Inject

class ApiService @Inject constructor (private val apiClient: ApiClient,
                 private val characterPagingSource: CharacterPagingSource) {

    companion object {
        const val MAX_ITEMS: Int = 20
        const val PREFECT_ITEMS: Int = 5
    }

    suspend fun getSingleCharacter(id: Int) : CharacterResponse {
        return apiClient.getSingleCharacter(id)
    }

    fun getAllCharacter() : Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(MAX_ITEMS, PREFECT_ITEMS),
            pagingSourceFactory = { characterPagingSource }
        ).flow
    }

}