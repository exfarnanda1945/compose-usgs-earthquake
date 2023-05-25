package com.example.composeusgsearthquake.data.dto

import com.google.gson.annotations.SerializedName

data class QuakeGeometryDto(

    @field:SerializedName("coordinates")
    val coordinates: List<Double?>? = null
)