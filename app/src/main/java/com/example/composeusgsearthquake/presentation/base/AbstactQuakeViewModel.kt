package com.example.composeusgsearthquake.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeusgsearthquake.presentation.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class AbstractQuakeViewModel<T:Any>:ViewModel() {
    abstract fun onEvent(event:T)
   protected  var _uiEvent = Channel<UiEvent>()
    protected  fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}