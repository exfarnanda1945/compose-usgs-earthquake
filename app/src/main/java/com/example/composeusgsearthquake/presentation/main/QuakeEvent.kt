package com.example.composeusgsearthquake.presentation.main

import com.example.composeusgsearthquake.domain.model.Quake

sealed class QuakeEvent {
    data class OnItemClick(val quake: Quake) : QuakeEvent()
    object OnSwipeRefresh:QuakeEvent()
}
