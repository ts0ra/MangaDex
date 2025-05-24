package com.ts0ra.core.data.source.remote.network

import com.ts0ra.core.data.source.remote.response.ListMangaResponse
import com.ts0ra.core.data.source.remote.response.cover.CoverResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("manga")
    suspend fun getMangaList(): ListMangaResponse

    @GET("cover/{id}")
    suspend fun getCover(
        @Path("id") id: String
    ): CoverResponse
}