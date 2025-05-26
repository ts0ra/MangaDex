package com.ts0ra.core.data.source.remote.network

import com.ts0ra.core.data.source.remote.response.cover.CoverResponse
import com.ts0ra.core.data.source.remote.response.mangalist.MangaListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("manga")
    suspend fun getMangaList(
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0
    ): MangaListResponse

    @GET("cover/{id}")
    suspend fun getCover(
        @Path("id") id: String
    ): CoverResponse
}