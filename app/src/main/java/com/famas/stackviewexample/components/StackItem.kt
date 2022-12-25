package com.famas.stackviewexample.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StackItemCard(
    expandedIndex: Int,
//    cardState: CardState,
    maxHeight: Dp,
    index: Int,
    onTap: () -> Unit,
    cardExpandContent: @Composable () -> Unit,
    cardCollapseContent: @Composable () -> Unit,
    cardHeader: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    totalHeight: Dp? = null,
    shape: Shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
    cardExpandedBgColor: Color? = null,
    cardCollapsedColor: Color? = null,
) {
    val animatedHeight by animateDpAsState(
        targetValue = if (expandedIndex >= index) {
            totalHeight ?: maxHeight.times(1f - index * 0.12f)
        } else if (expandedIndex + 1 == index) {
            maxHeight.times(
                0.12f
            )
        } else 0.dp
    )


    val animatedColor by animateColorAsState(
        targetValue = if (expandedIndex >= index) {
            cardExpandedBgColor ?: MaterialTheme.colors.primary
        } else cardCollapsedColor ?: MaterialTheme.colors.surface
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .pointerInput(Unit) {
                detectTapGestures {
                    onTap()
                }
            },
        shape = shape,
        color = animatedColor
    ) {
        if (expandedIndex == index) {
            cardExpandContent()
        } else if (expandedIndex < index) {
            cardHeader()
        } else {
            cardCollapseContent()
        }
    }
}