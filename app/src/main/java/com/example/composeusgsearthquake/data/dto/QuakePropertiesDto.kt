package com.example.composeusgsearthquake.data.dto

import com.google.gson.annotations.SerializedName

data class QuakePropertiesDto(

    @field:SerializedName("tsunami")
    val tsunami: Int? = null,

    @field:SerializedName("mag")
    val mag: Double? = null,

    @field:SerializedName("place")
    val place: String? = null,

    @field:SerializedName("time")
    val time: Long? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)
