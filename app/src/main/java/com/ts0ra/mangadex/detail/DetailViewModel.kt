package com.ts0ra.mangadex.detail

import androidx.lifecycle.ViewModel
import com.ts0ra.core.domain.model.Manga
import com.ts0ra.core.domain.usecase.MangaUseCase

class DetailViewModel(private val mangaUseCase: MangaUseCase) : ViewModel() {
    var mangaDetail: Manga? = null
    fun setFavoriteManga(manga: Manga, isFavorite: Boolean) {
        mangaUseCase.setFavoriteManga(manga, isFavorite)
    }
}