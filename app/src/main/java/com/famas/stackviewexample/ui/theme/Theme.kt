package com.famas.stackviewexample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryRed,
    primaryVariant = Purple700,
    secondary = Color(0xffEEEEEE),
    onSurface = Color(0xffEEEEEE),
    onPrimary = Color(0xFF771E34),
    surface = ColorSurfaceDisabled
)

//private val LightColorPalette = lightColors(
//    primary = PrimaryRed,
//    primaryVariant = Purple700,
//    secondary = Teal200
//
//    /* Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
//    */
//)

@Composable
fun StackViewExampleTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}