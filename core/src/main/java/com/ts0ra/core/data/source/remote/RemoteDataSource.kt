package com.ts0ra.core.data.source.remote

import android.util.Log
import com.google.gson.JsonParser
import com.ts0ra.core.data.source.remote.network.ApiResponse
import com.ts0ra.core.data.source.remote.network.ApiService
import com.ts0ra.core.data.source.remote.response.cover.Data
import com.ts0ra.core.data.source.remote.response.mangalist.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllManga(): Flow<ApiResponse<List<DataItem>>> {
        return flow {
            try {
                val response = apiService.getMangaList()
                val dataArray = response.data
                val nonNullDataArray = dataArray?.filterNotNull() ?: emptyList()
                if (nonNullDataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(nonNullDataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                errorBody?.let {
                    val jsonElement = JsonParser.parseString(it)
                    val detail = jsonElement
                        .asJsonObject["errors"]
                        .asJsonArray[0]
                        .asJsonObject["detail"]
                        .asString
                    emit(ApiResponse.Error(detail))
                    Log.e("RemoteDataSource", detail)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCover(id: String): Flow<ApiResponse<Data>> {
        return flow {
            try {
                val response = apiService.getCover(id)
                val data = response.data
                if (data != null) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                errorBody?.let {
                    val jsonElement = JsonParser.parseString(it)
                    val detail = jsonElement
                        .asJsonObject["errors"]
                        .asJsonArray[0]
                        .asJsonObject["detail"]
                        .asString
                    emit(ApiResponse.Error(detail))
                    Log.e("RemoteDataSource", detail)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}