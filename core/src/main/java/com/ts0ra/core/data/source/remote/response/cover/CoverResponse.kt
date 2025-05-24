package com.ts0ra.core.data.source.remote.response.cover

import com.google.gson.annotations.SerializedName

data class CoverResponse(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("response")
	val response: String? = null
)