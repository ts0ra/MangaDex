package com.ts0ra.core.domain.usecase

import com.ts0ra.core.data.Resource
import com.ts0ra.core.domain.model.Manga
import com.ts0ra.core.domain.repository.IMangaRepository
import kotlinx.coroutines.flow.Flow

class MangaInteractor(private val mangaRepository: IMangaRepository): MangaUseCase {
    override fun getAllManga(): Flow<Resource<List<Manga>>> = mangaRepository.getAllManga()
    override fun getFavoriteManga(): Flow<List<Manga>> = mangaRepository.getFavoriteManga()
    override fun setFavoriteManga(manga: Manga, state: Boolean) = mangaRepository.setFavoriteManga(manga, state)
}