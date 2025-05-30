package com.ts0ra.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ts0ra.core.data.source.local.entity.MangaEntity

@Database(entities = [MangaEntity::class], version = 1, exportSchema = false)
abstract class MangaDatabase : RoomDatabase() {
    abstract fun mangaDao(): MangaDao
}