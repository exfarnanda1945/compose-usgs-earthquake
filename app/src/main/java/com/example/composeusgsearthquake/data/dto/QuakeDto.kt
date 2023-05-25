package com.example.composeusgsearthquake.data.dto

import com.google.gson.annotations.SerializedName

data class QuakeDto(

    @field:SerializedName("features")
    val features: List<QuakeFeaturesItemDto?>? = null,

    @field:SerializedName("metadata")
    val metadata: QuakeMetadataDto? = null
)

