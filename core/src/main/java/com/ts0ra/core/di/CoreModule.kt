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
import okhttp3.CertificatePinner
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
        val hostname = "api.mangadex.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/uR/2lm3YfmSpxOYj4Us0KozMmRu5GvJO2kMuLLVm24Y=")
            .add(hostname, "sha256/0Bbh/jEZSKymTy3kTOhsmlHKBB32EDu1KojrP3YfV9c=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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