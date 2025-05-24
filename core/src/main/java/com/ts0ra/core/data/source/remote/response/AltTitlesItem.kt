package com.ts0ra.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AltTitlesItem(

	@field:SerializedName("property2")
	val property2: String? = null,

	@field:SerializedName("property1")
	val property1: String? = null
)