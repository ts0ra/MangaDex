package com.ts0ra.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ts0ra.core.domain.usecase.MangaUseCase

class FavoriteViewModel(mangaUseCase: MangaUseCase) : ViewModel() {
    val favoriteManga = mangaUseCase.getFavoriteManga().asLiveData()
}