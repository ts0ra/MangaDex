package com.ts0ra.core.data.source.remote.response.cover

import com.google.gson.annotations.SerializedName

data class RelationshipsItem(

	@field:SerializedName("related")
	val related: String? = null,

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)