package com.ts0ra.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMangaResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<MangaResponse?>? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("limit")
	val limit: Int? = null
)