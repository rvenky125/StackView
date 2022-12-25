package com.famas.stackviewexample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.famas.stackviewexample.R
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceMedium

@Composable
fun PayExpanded(modifier: Modifier = Modifier) {

    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.faceid))
    val progress by animateLottieCompositionAsState(composition = lottieComposition)

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(SpaceLarge * 2f))
        Text(
            text = "TOTAL",
            color = ColorOnSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2.4 BTC",
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth().padding(top = SpaceMedium),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        LottieAnimation(composition = lottieComposition, progress = { progress }, modifier = Modifier.fillMaxHeight(0.7f))

        Text(
            text = "Scan face to complete",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}