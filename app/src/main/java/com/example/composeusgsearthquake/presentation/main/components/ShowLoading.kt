package com.example.composeusgsearthquake.presentation.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ShowLoading(isLoadingMorePaginate:Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Loading when get more data in paginate
        if(isLoadingMorePaginate){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text(text = "Fetch more...")
            }
        }else{
            // Loading when first get
            CircularProgressIndicator()
        }
    }

}