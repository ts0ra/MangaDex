package com.ts0ra.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manga")
data class MangaEntity(
    @PrimaryKey
    val mangaId: String,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val description: String,

    @ColumnInfo
    val coverArtId: String,

    @ColumnInfo
    val coverArtFile: String,

    @ColumnInfo
    var isFavorite: Boolean = false
)
