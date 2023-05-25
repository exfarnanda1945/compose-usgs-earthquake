package com.example.composeusgsearthquake.data.dto

import com.google.gson.annotations.SerializedName

data class QuakeFeaturesItemDto(

    @field:SerializedName("geometry")
    val geometry: QuakeGeometryDto? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("properties")
    val properties: QuakePropertiesDto? = null
)