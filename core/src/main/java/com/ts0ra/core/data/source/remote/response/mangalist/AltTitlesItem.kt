package com.ts0ra.core.data.source.remote.response.mangalist

import com.google.gson.annotations.SerializedName

data class AltTitlesItem(

	@field:SerializedName("ko")
	val ko: String? = null,

	@field:SerializedName("zh")
	val zh: String? = null,

	@field:SerializedName("ja")
	val ja: String? = null,

	@field:SerializedName("ru")
	val ru: String? = null,

	@field:SerializedName("ja-ro")
	val jaRo: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("vi")
	val vi: String? = null,

	@field:SerializedName("ne")
	val ne: String? = null,

	@field:SerializedName("fr")
	val fr: String? = null,

	@field:SerializedName("uk")
	val uk: String? = null,

	@field:SerializedName("he")
	val he: String? = null,

	@field:SerializedName("zh-hk")
	val zhHk: String? = null
)