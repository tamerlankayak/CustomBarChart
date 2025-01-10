package com.example.custombarchart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.custombarchart.ui.theme.CustomBarChartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomBarChartTheme {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.height(200.dp))
                    MyBarChart()

                }
            }
        }
    }
}


@Composable
fun MyBarChart() {
    val barData = listOf(
        BarData("Fvr", 30F),
        BarData("Mart", 50F),
        BarData("Mart", 40F),
        BarData("Apr", 10F),
        BarData("May", 20F),
    )

    val maxValue = barData.maxOf { it.value }
    val barColor = Color.Blue
    val backgroundBarColor = Color.LightGray
    val spacing = 16.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(horizontal = 40.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            val barWidth = (size.width / barData.size) - spacing.toPx()
            val barSpacing = spacing.toPx()

            barData.forEachIndexed { index, bar ->
                val barHeight = size.height * (bar.value / maxValue)
                val fullHeight = size.height // For the 100% background bar
                val x = index * (barWidth + barSpacing)

                // Draw the background bar at 100%
                drawRoundRect(
                    cornerRadius = CornerRadius(20f, 20f),
                    color = backgroundBarColor,
                    topLeft = Offset(x, 0f),
                    size = Size(barWidth, fullHeight)
                )

                // Draw the actual data bar on top
                drawRoundRect(
                    cornerRadius = CornerRadius(20f, 20f),
                    color = barColor,
                    topLeft = Offset(x, size.height - barHeight),
                    size = Size(barWidth, barHeight)
                )
            }
        }
    }
}

data class BarData(val label: String, val value: Float)