package com.ts0ra.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ts0ra.core.data.source.local.entity.MangaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDao {
    @Query("SELECT * FROM manga")
    fun getAllManga(): Flow<List<MangaEntity>>

    @Query("SELECT * FROM manga where isFavorite = 1")
    fun getFavoriteManga(): Flow<List<MangaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(manga: List<MangaEntity>)

    @Update
    fun updateFavoriteManga(manga: MangaEntity)
}