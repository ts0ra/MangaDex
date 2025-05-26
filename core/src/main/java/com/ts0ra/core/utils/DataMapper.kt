package com.ts0ra.core.utils

import android.util.Log
import com.ts0ra.core.data.source.local.entity.MangaEntity
import com.ts0ra.core.data.source.remote.response.mangalist.DataItem
import com.ts0ra.core.domain.model.Manga

object DataMapper {    fun mapResponsesToEntities(input: List<DataItem>): List<MangaEntity> {
        return input.mapNotNull { item ->
            try {
                val id = item.id ?: return@mapNotNull null
                val attributes = item.attributes ?: return@mapNotNull null
                val title = attributes.title?.en ?: return@mapNotNull null
                val description = attributes.description?.en ?: "No description available"

                val coverArtId = item.relationships
                    ?.filterNotNull()
                    ?.firstOrNull { it.type == "cover_art" }
                    ?.id ?: return@mapNotNull null
                
                MangaEntity(
                    mangaId = id,
                    title = title,
                    description = description,
                    coverArtId = coverArtId,
                    coverArtFile = "",
                    isFavorite = false,
                )
            } catch (e: Exception) {
                Log.w("DataMapper", "Failed to map manga item: ${e.message}")
                null
            }
        }
    }

    fun mapEntitiesToDomain(input: List<MangaEntity>): List<Manga> =
        input.map {
            Manga(
                mangaId = it.mangaId,
                title = it.title,
                description = it.description,
                coverArtId = it.coverArtId,
                coverArtFile = it.coverArtFile,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Manga) = MangaEntity(
        mangaId = input.mangaId,
        title = input.title,
        description = input.description,
        coverArtId = input.coverArtId,
        coverArtFile = input.coverArtFile,
        isFavorite = input.isFavorite
    )
}