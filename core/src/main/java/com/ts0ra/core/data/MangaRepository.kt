package com.ts0ra.core.data

import com.ts0ra.core.data.source.local.LocalDataSource
import com.ts0ra.core.data.source.remote.RemoteDataSource

class MangaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,

) {
}