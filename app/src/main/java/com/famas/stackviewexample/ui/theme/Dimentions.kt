package com.famas.stackviewexample.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val SpaceVerySmall = 4.dp
val SpaceSemiSmall = 6.dp
val SpaceSmall = 8.dp
val SpaceMedium = 12.dp
val SpaceSemiLarge = 16.dp
val SpaceLarge = 20.dp


fun Modifier.defaultScreenPadding(): Modifier = padding(horizontal = SpaceLarge)