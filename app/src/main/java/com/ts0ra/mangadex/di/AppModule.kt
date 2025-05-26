package com.ts0ra.mangadex.di

import com.ts0ra.core.domain.usecase.MangaInteractor
import com.ts0ra.core.domain.usecase.MangaUseCase
import com.ts0ra.mangadex.MainViewModel
import com.ts0ra.mangadex.detail.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MangaUseCase> { MangaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}