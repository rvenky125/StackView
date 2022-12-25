package com.famas.stackviewexample

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) {
        toPx()
    }
}


@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    return ScreenSize(screenWidth, screenHeight)
}


data class ScreenSize(
    val width: Dp,
    val height: Dp
)