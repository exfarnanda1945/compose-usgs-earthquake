package com.example.composeusgsearthquake.presentation

import com.example.composeusgsearthquake.domain.model.Quake

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class NavigateToDetail(val item: Quake) : UiEvent()
}
