package com.ts0ra.core.data

import com.ts0ra.core.data.source.local.LocalDataSource
import com.ts0ra.core.data.source.local.entity.MangaEntity
import com.ts0ra.core.data.source.remote.RemoteDataSource
import com.ts0ra.core.data.source.remote.network.ApiResponse
import com.ts0ra.core.data.source.remote.response.mangalist.DataItem
import com.ts0ra.core.domain.model.Manga
import com.ts0ra.core.domain.repository.IMangaRepository
import com.ts0ra.core.utils.AppExecutors
import com.ts0ra.core.utils.DataMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MangaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMangaRepository {
    override fun getAllManga(): Flow<Resource<List<Manga>>> =
        object : NetworkBoundResource<List<Manga>, List<DataItem>>() {
            override fun loadFromDB(): Flow<List<Manga>> {
                return localDataSource.getAllManga().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Manga>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
                remoteDataSource.getAllManga()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val mangaList = DataMapper.mapResponsesToEntities(data)

                // Fetch all covers concurrently for better performance
                val updatedMangaList = coroutineScope {
                    mangaList.map { manga ->
                        async {
                            try {
                                val coverResponse = remoteDataSource.getCover(manga.coverArtId).first()
                                when (coverResponse) {
                                    is ApiResponse.Success -> {
                                        val fileName = coverResponse.data.attributes?.fileName ?: ""
                                        MangaEntity(
                                            mangaId = manga.mangaId,
                                            title = manga.title,
                                            description = manga.description,
                                            coverArtId = manga.coverArtId,
                                            coverArtFile = fileName,
                                            isFavorite = manga.isFavorite
                                        )
                                    }
                                    else -> manga
                                }
                            } catch (e: Exception) {
                                manga
                            }
                        }
                    }.awaitAll()
                }

                localDataSource.insertManga(updatedMangaList)
            }
        }.asFlow()

    override fun getFavoriteManga(): Flow<List<Manga>> {
        return localDataSource.getFavoriteManga().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteManga(manga: Manga, state: Boolean) {
        val mangaEntity = DataMapper.mapDomainToEntity(manga)
        appExecutors.diskIO().execute { localDataSource.setFavoriteManga(mangaEntity, state) }
    }
}
