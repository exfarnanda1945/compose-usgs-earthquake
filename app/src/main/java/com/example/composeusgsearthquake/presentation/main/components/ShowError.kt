package com.example.composeusgsearthquake.presentation.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowError(isErrorMorePaginate:Boolean,onRefresh:() -> Unit) {
    val paddingVerticalBox = if (isErrorMorePaginate) 8.dp else 0.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(paddingVerticalBox)
    ) {
       if(isErrorMorePaginate){
           Column(horizontalAlignment = Alignment.CenterHorizontally) {
               Text(text = "Failed fetch pagination")
               Button(onClick = onRefresh) {
                   Text(text = "Refresh")
               }
           }
       }else{
           // Show error for first get data
           Column(horizontalAlignment = Alignment.CenterHorizontally) {
               Text(text = "Error, Cannot fetch data !")
               Button(onClick = onRefresh) {
                   Text(text = "Refresh")
               }
           }
       }
    }

}