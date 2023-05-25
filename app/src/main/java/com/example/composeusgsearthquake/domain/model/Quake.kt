package com.example.composeusgsearthquake.domain.model

data class Quake(
    val id: String,
    val mag: Double,
    val place: String,
    val time: Long,
    val tsunami: Boolean,
    val type: String,
    val title: String,
    val latitude: Double,
    val longitude: Double,
)
