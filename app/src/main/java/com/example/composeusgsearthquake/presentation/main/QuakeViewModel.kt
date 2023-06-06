package com.example.composeusgsearthquake.presentation.main

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.composeusgsearthquake.data.repository.QuakeRepository
import com.example.composeusgsearthquake.presentation.UiEvent
import com.example.composeusgsearthquake.presentation.base.AbstractQuakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuakeViewModel @Inject constructor(
    repository: QuakeRepository
) : AbstractQuakeViewModel<QuakeEvent>() {
    val quakeList = repository.getList().cachedIn(viewModelScope)
    private var _refreshing = MutableStateFlow(false)
    val refreshing = _refreshing

    val uiEvent =
        _uiEvent
            .receiveAsFlow()

    override fun onEvent(event: QuakeEvent) {
        when (event) {
            is QuakeEvent.OnItemClick -> {
                sendUiEvent(UiEvent.NavigateToDetail(event.quake))
            }

            QuakeEvent.OnSwipeRefresh -> {

            }
        }
    }

    fun setRefreshing(status:Boolean){
        viewModelScope.launch {
            _refreshing.emit(status)
        }
    }


}