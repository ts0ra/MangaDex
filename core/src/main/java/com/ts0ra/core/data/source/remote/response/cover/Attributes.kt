package com.ts0ra.core.data.source.remote.response.cover

import com.google.gson.annotations.SerializedName

data class Attributes(

	@field:SerializedName("volume")
	val volume: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("locale")
	val locale: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)