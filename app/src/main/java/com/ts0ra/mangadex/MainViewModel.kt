package com.ts0ra.mangadex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ts0ra.core.domain.usecase.MangaUseCase

class MainViewModel(mangaUseCase: MangaUseCase) : ViewModel() {
    val manga = mangaUseCase.getAllManga().asLiveData()
}