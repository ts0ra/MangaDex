package com.ts0ra.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Attributes(

	@field:SerializedName("lastVolume")
	val lastVolume: String? = null,

	@field:SerializedName("latestUploadedChapter")
	val latestUploadedChapter: String? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("description")
	val description: Description? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("originalLanguage")
	val originalLanguage: String? = null,

	@field:SerializedName("lastChapter")
	val lastChapter: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("altTitles")
	val altTitles: List<AltTitlesItem?>? = null,

	@field:SerializedName("publicationDemographic")
	val publicationDemographic: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("isLocked")
	val isLocked: Boolean? = null,

	@field:SerializedName("availableTranslatedLanguages")
	val availableTranslatedLanguages: List<String?>? = null,

	@field:SerializedName("chapterNumbersResetOnNewVolume")
	val chapterNumbersResetOnNewVolume: Boolean? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("contentRating")
	val contentRating: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("group")
	val group: String? = null
)