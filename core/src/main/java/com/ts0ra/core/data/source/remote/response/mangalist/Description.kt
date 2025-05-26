package com.ts0ra.core.data.source.remote.response.mangalist

import com.google.gson.annotations.SerializedName

data class Description(

	@field:SerializedName("pt")
	val pt: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("fr")
	val fr: String? = null,

	@field:SerializedName("ja")
	val ja: String? = null,

	@field:SerializedName("ru")
	val ru: String? = null,

	@field:SerializedName("vi")
	val vi: String? = null
)