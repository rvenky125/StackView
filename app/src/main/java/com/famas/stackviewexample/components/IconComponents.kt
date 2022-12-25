package com.famas.stackviewexample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.famas.stackviewexample.R

@Composable
fun FromIcon(modifier: Modifier = Modifier) {
    val circlePainter = painterResource(id = R.drawable.from)
    val linePainter = painterResource(id = R.drawable.line)
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Icon(painter = circlePainter, contentDescription = null, tint = MaterialTheme.colors.primary)
        Icon(painter = linePainter, contentDescription = null, tint = MaterialTheme.colors.primary)
    }
}