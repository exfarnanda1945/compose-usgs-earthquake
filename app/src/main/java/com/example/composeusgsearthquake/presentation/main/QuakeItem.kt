package com.example.composeusgsearthquake.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeusgsearthquake.domain.model.Quake
import com.example.composeusgsearthquake.ui.theme.BrightYellow
import com.example.composeusgsearthquake.ui.theme.RedRose
import com.example.composeusgsearthquake.ui.theme.SunGlow
import com.example.composeusgsearthquake.ui.theme.UfoGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuakeItem(
    quake: Quake,
    onItemClick: (quake: Quake) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .shadow(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = setBackgroundColor(quake.mag),
            contentColor = Color.White
        ),
        onClick = { onItemClick(quake) }
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            )
        ) {
            Text(
                text = quake.title,
                modifier = Modifier.padding(bottom = 8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(text =  SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date(quake.time)))
        }

    }
}

fun setBackgroundColor(mag: Double): Color {
    return when {
        mag < 5.0 -> UfoGreen
        mag < 6.0 -> SunGlow
        mag < 7.0 -> BrightYellow
        else -> RedRose
    }

}

