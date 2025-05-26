package com.ts0ra.core.data.source.remote.response.mangalist

import com.google.gson.annotations.SerializedName

data class MangaListResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("limit")
	val limit: Int? = null
)