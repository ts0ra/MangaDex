package com.ts0ra.core.data.source.remote.response.mangalist

import com.google.gson.annotations.SerializedName

data class TagsItem(

	@field:SerializedName("relationships")
	val relationships: List<Any?>? = null,

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)