package com.ts0ra.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manga(
    val mangaId: String,
    val title: String,
    val description: String,
    val coverArtId: String,
    val coverArtFile: String,
    var isFavorite: Boolean
) : Parcelable
