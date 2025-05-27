package com.ts0ra.core.di

import androidx.room.Room
import com.ts0ra.core.data.MangaRepository
import com.ts0ra.core.data.source.local.LocalDataSource
import com.ts0ra.core.data.source.local.room.MangaDatabase
import com.ts0ra.core.data.source.remote.RemoteDataSource
import com.ts0ra.core.data.source.remote.network.ApiService
import com.ts0ra.core.domain.repository.IMangaRepository
import com.ts0ra.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MangaDatabase>().mangaDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MangaDatabase::class.java, "Manga.db"
        ).fallbackToDestructiveMigration(false)
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mangadex.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMangaRepository> {
        MangaRepository(
            get(),
            get(),
            get()
        )
    }
}