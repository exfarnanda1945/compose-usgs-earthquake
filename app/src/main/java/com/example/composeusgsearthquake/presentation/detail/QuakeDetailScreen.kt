package com.example.composeusgsearthquake.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeusgsearthquake.R
import com.example.composeusgsearthquake.domain.model.Quake
import com.example.composeusgsearthquake.presentation.UiEvent
import com.example.composeusgsearthquake.ui.theme.PictonBlue
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuakeDetailScreen(
    navigator: DestinationsNavigator,
    quake: Quake,
    viewModel: QuakeDetailViewModel = hiltViewModel()
) {
    val epicenter = LatLng(quake.latitude, quake.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(epicenter, 6f)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{
            when(it){
                is UiEvent.NavigateToDetail -> {}
                UiEvent.PopBackStack -> navigator.popBackStack()
            }
        }
    }

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Detail", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 24.sp
                    )
                )
            },
            modifier = Modifier.shadow(8.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = PictonBlue
            ),
            navigationIcon = {
                IconButton(onClick = {
                    viewModel.onEvent(QuakeDetailEvent.OnBack)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = Color.White,
                        contentDescription = "Back"
                    )
                }
            }
        )
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                zoomGesturesEnabled = false,
                scrollGesturesEnabled = false,
                tiltGesturesEnabled = false,
            )
        ) {
            Marker(
                state = MarkerState(position = epicenter),
                title = "${quake.mag} M",
                snippet = quake.place,
            )
        }
    }
}