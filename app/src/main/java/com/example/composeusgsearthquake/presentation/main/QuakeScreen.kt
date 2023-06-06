package com.example.composeusgsearthquake.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.composeusgsearthquake.presentation.UiEvent
import com.example.composeusgsearthquake.presentation.destinations.QuakeDetailScreenDestination
import com.example.composeusgsearthquake.presentation.main.components.ShowError
import com.example.composeusgsearthquake.presentation.main.components.ShowLoading
import com.example.composeusgsearthquake.ui.theme.PictonBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun QuakeScreen(
    navigator:DestinationsNavigator,
    viewModel: QuakeViewModel = hiltViewModel()
) {
    val quakeList = viewModel.quakeList.collectAsLazyPagingItems()
    val refreshing by viewModel.refreshing.collectAsStateWithLifecycle()
    val refreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh ={
        viewModel.setRefreshing(true)
        quakeList.refresh()
        viewModel.setRefreshing(false)
    })


    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.NavigateToDetail ->  navigator.navigate(QuakeDetailScreenDestination(event.item))
                UiEvent.PopBackStack -> {}
            }
        }
    }

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Quake Compose", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 24.sp
                    )
                )
            },
            modifier = Modifier.shadow(8.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = PictonBlue
            )
        )
        when (quakeList.loadState.refresh) {
            // If First Fetch Loading
            is LoadState.Loading -> ShowLoading(isLoadingMorePaginate = false)
            // If First Fetch Error
            is LoadState.Error -> ShowError(isErrorMorePaginate = false) {
                quakeList.refresh()
            }
            // Show Data
            is LoadState.NotLoading -> Box(Modifier.pullRefresh(refreshState)) {
                if(!refreshing){
                    LazyColumn(
                        Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                            .fillMaxSize()
                    ) {
                        items(quakeList) { quake ->
                            QuakeItem(quake = quake!!, onItemClick = { item ->
                                viewModel.onEvent(QuakeEvent.OnItemClick(item))
                            })
                        }
                        when (quakeList.loadState.append) {
                            is LoadState.Loading -> item {
                                ShowLoading(isLoadingMorePaginate = true)
                            }

                            is LoadState.Error -> item {
                                ShowError(isErrorMorePaginate = true) {
                                    quakeList.retry()
                                }
                            }

                            else -> {}
                        }
                    }
                }
                PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
            }

            else -> {}
        }

    }
}
