package com.dlebal.androidtechnicaltest.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dlebal.androidtechnicaltest.core.common.paging.CharacterPagingSource
import com.dlebal.androidtechnicaltest.data.network.ApiService
import com.dlebal.androidtechnicaltest.domain.Repository
import com.dlebal.androidtechnicaltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val characterPagingSource: CharacterPagingSource
) : Repository {

    companion object {
        const val MAX_ITEMS: Int = 20
        const val PREFECT_ITEMS: Int = 4
    }

    override suspend fun getSingleCharacter(id: Int): CharacterModel {
        return apiService.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacter(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(MAX_ITEMS, PREFECT_ITEMS),
            pagingSourceFactory = { characterPagingSource }
        ).flow
    }

}