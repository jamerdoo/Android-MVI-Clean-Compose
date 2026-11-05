package com.dlebal.androidtechnicaltest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dlebal.androidtechnicaltest.data.database.entity.CharacterOfDayEntity

const val DatabaseName = "CesarTestDatabase"

@Database(entities = [CharacterOfDayEntity::class], version = 1)
abstract class Database : RoomDatabase() 