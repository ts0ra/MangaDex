package com.ts0ra.core.domain.repository

import com.ts0ra.core.data.Resource
import com.ts0ra.core.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface IMangaRepository {
    fun getAllManga(): Flow<Resource<List<Manga>>>
    fun getFavoriteManga(): Flow<List<Manga>>
    fun setFavoriteManga(manga: Manga, state: Boolean)
}