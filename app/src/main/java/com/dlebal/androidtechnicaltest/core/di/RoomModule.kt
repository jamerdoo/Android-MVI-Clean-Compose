package com.cesar.programingapp.core.di

import android.content.Context
import androidx.room.Room
import com.dlebal.androidtechnicaltest.data.database.Database
import com.dlebal.androidtechnicaltest.data.database.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : Database {
        return Room.databaseBuilder(context, Database::class.java, DatabaseName).build()
    }

}