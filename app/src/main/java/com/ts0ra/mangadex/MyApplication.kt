package com.ts0ra.mangadex

import android.app.Application
import com.ts0ra.core.di.databaseModule
import com.ts0ra.core.di.networkModule
import com.ts0ra.core.di.repositoryModule
import com.ts0ra.mangadex.di.useCaseModule
import com.ts0ra.mangadex.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}