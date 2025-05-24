package com.ts0ra.core.utils

import com.ts0ra.core.data.source.local.entity.MangaEntity
import com.ts0ra.core.data.source.remote.response.MangaResponse
import com.ts0ra.core.domain.model.Manga

object DataMapper {
    fun mapResponsesToEntities(input: List<MangaResponse>, coverArtUrl: String): List<MangaEntity> {
        val mangaList = ArrayList<MangaEntity>()
        input.map {
            val coverArtId = it.relationships!!
                .firstOrNull { it!!.type == "cover_art" }
                ?.id
            val manga = MangaEntity(
                mangaId = it.id.toString(),
                title = it.attributes!!.title!!.property1!!,
                description = it.attributes.description!!.property1!!,
                coverArtId = coverArtId!!,
                coverArtFile = coverArtUrl,
                isFavorite = false,
            )
            mangaList.add(manga)
        }
        return mangaList
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