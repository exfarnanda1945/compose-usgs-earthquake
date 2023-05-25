package com.example.composeusgsearthquake.data.dto

import com.google.gson.annotations.SerializedName

data class QuakeMetadataDto(

    @field:SerializedName("offset")
    val offset: Int? = null,

    @field:SerializedName("limit")
    val limit: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null
)