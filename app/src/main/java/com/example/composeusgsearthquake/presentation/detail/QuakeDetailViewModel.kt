package com.example.composeusgsearthquake.presentation.detail

import com.example.composeusgsearthquake.presentation.UiEvent
import com.example.composeusgsearthquake.presentation.base.AbstractQuakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class QuakeDetailViewModel @Inject constructor() : AbstractQuakeViewModel<QuakeDetailEvent>() {

    val uiEvent =
        _uiEvent
            .receiveAsFlow()

    override fun onEvent(event: QuakeDetailEvent) {
        when (event) {
            QuakeDetailEvent.OnBack -> sendUiEvent(UiEvent.PopBackStack)
        }
    }

}