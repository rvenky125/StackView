package com.famas.stackviewexample

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.famas.stackviewexample.components.ScheduleCalendarCollapse
import com.famas.stackviewexample.components.ScheduleCalendarExpanded
import com.famas.stackviewexample.components.SeatsExpand
import com.famas.stackviewexample.components.TopBar
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.ColorSurfaceDisabled
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceSemiLarge
import com.famas.stackviewexample.ui.theme.SpaceSemiSmall

@Composable
fun StackScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        StackLt()
    }
}


@Composable
fun StackLt(
    modifier: Modifier = Modifier
) {
    var expandedCardIndex by remember {
        mutableStateOf(0)
    }


    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        StackItemCard(
            expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            index = 0,
            cardCollapsedColor = MaterialTheme.colors.surface,
            cardExpandedBgColor = MaterialTheme.colors.background,
            cardExpandContent = {
                ScheduleCalendarExpanded()
            },
            cardCollapseContent = {
                ScheduleCalendarCollapse()
            },
            cardHeader = {

            },
            onTap = {
                expandedCardIndex = 0
            }
        )
        StackItemCard(
            expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            totalHeight = maxHeight - 170.dp,
            cardCollapsedColor = ColorSurfaceDisabled,
            cardExpandedBgColor = MaterialTheme.colors.primary,
            index = 1,
            cardExpandContent = {
                SeatsExpand()
            },
            cardCollapseContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "Card2")
                }
            },
            cardHeader = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "Card2")
                }
            },
            onTap = {
                expandedCardIndex = 1
            }
        )
        StackItemCard(
            expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            cardCollapsedColor = MaterialTheme.colors.surface,
            cardExpandedBgColor = MaterialTheme.colors.secondary,
            index = 2,
            cardExpandContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "Card3")
                }
            },
            cardCollapseContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "Card3")
                }
            },
            cardHeader = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "Card3")
                }
            },
            onTap = {
                expandedCardIndex = 2
            }
        )
    }
}

val colors = listOf(Color.Red, Color.White, Color.Green)

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