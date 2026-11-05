package com.dlebal.androidtechnicaltest.core.di

import com.dlebal.androidtechnicaltest.data.RepositoryImpl
import com.dlebal.androidtechnicaltest.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}