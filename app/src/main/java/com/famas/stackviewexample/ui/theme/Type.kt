package com.famas.stackviewexample.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.R

val IbmFlexFontFamily = FontFamily(
    Font(R.font.ibm_plex_sans_bold, weight = FontWeight.Bold),
    Font(R.font.ibm_plex_sans_light, weight = FontWeight.Light),
    Font(R.font.ibm_plex_sans_medium, weight = FontWeight.Medium),
    Font(R.font.ibm_plex_sans_regular, weight = FontWeight.Normal),
    Font(R.font.ibm_plex_sans_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.ibm_plex_sans_semibold, weight = FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    defaultFontFamily = IbmFlexFontFamily
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)