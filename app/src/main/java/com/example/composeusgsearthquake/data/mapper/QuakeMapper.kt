package com.example.composeusgsearthquake.data.mapper

import com.example.composeusgsearthquake.data.dto.QuakeDto
import com.example.composeusgsearthquake.domain.model.Quake

fun QuakeDto.toQuake():List<Quake>{
    return this.features!!.distinct().map {
        val item = it!!
        val geometryItem = item.geometry!!
        val propertiesItem = item.properties!!

         Quake(
            id = item.id ?: "",
            type = propertiesItem.type ?: "",
            tsunami = propertiesItem.tsunami == 1,
            latitude = geometryItem.coordinates?.get(1) ?: 0.0,
            longitude = geometryItem.coordinates?.get(0) ?: 0.0,
            mag = propertiesItem.mag ?: 0.0,
            place = propertiesItem.place ?: "",
            time = propertiesItem.time ?: 0.toLong(),
            title = propertiesItem.title ?: ""
        )
    }
}