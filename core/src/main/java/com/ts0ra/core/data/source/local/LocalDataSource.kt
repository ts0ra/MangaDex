package com.ts0ra.core.data.source.local

import com.ts0ra.core.data.source.local.entity.MangaEntity
import com.ts0ra.core.data.source.local.room.MangaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mangaDao: MangaDao) {
    fun getAllManga(): Flow<List<MangaEntity>> = mangaDao.getAllManga()
    fun getFavoriteManga(): Flow<List<MangaEntity>> = mangaDao.getFavoriteManga()
    suspend fun insertManga(mangaList: List<MangaEntity>) = mangaDao.insertManga(mangaList)
    fun setFavoriteManga(manga: MangaEntity, isFavorite: Boolean) {
        manga.isFavorite = isFavorite
        mangaDao.updateFavoriteManga(manga)
    }
}