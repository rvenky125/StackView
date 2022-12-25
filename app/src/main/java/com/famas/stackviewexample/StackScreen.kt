package com.famas.stackviewexample

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.components.PayExpanded
import com.famas.stackviewexample.components.ScheduleCalendarCollapse
import com.famas.stackviewexample.components.ScheduleCalendarExpanded
import com.famas.stackviewexample.components.SeatsExpand
import com.famas.stackviewexample.components.StackItemCard
import com.famas.stackviewexample.components.TopBar
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceSmall
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@Composable
fun StackScreen(modifier: Modifier = Modifier) {
    var expandedCardIndex by remember {
        mutableStateOf(0)
    }
    Column(modifier = modifier.fillMaxSize()) {
        TopBar()
        StackLt(expandedCardIndex) {
            expandedCardIndex = it
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun StackLt(
    expandedCardIndex: Int,
    modifier: Modifier = Modifier,
    setExpandedCardIndex: (Int) -> Unit,
) {
    val context = LocalContext.current
    val (selectedTimeChipIndex, setSelectedTimeChipIndex) = remember {
        mutableStateOf<Int?>(null)
    }

    val (selectedStartDateIndex, setSelectedStartDateIndex) = remember {
        mutableStateOf<Int?>(null)
    }

    val (selectedEndDateIndex, setSelectedEndDateIndex) = remember {
        mutableStateOf<Int?>(null)
    }

    val adultsPagerState = rememberPagerState()
    val childrenPagerState = rememberPagerState()

    val (isChildrenExist, setIsChildrenExist) = remember {
        mutableStateOf(false)
    }

    val seatsCardEnabled =
        selectedStartDateIndex != null && selectedEndDateIndex != null && selectedTimeChipIndex != null

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        StackItemCard(expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            index = 0,
            cardCollapsedColor = MaterialTheme.colors.surface,
            cardExpandedBgColor = MaterialTheme.colors.background,
            cardExpandContent = {
                ScheduleCalendarExpanded(
                    selectedTimeChipIndex = selectedTimeChipIndex,
                    selectedStartDateIndex = selectedStartDateIndex,
                    selectedEndDateIndex = selectedEndDateIndex,
                    setSelectedTimeChipIndex = setSelectedTimeChipIndex,
                    setSelectedStartDateIndex = setSelectedStartDateIndex,
                    setSelectedEndDateIndex = setSelectedEndDateIndex
                )
            },
            cardCollapseContent = {
                ScheduleCalendarCollapse(
                    selectedStartDateIndex,
                    selectedEndDateIndex,
                    selectedTimeChipIndex,
                    modifier = Modifier.height(maxHeight * 0.23f)
                )
            },
            cardHeader = {
            },
            onTap = {
                setExpandedCardIndex(0)
            })
        StackItemCard(expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            totalHeight = maxHeight * 0.77f,
            cardCollapsedColor = if (seatsCardEnabled) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            cardExpandedBgColor = MaterialTheme.colors.primary,
            index = 1,
            cardExpandContent = {
                SeatsExpand(
                    adultsPagerState, childrenPagerState, isChildrenExist, setIsChildrenExist
                )
            },
            cardCollapseContent = {
                Text(
                    text = "${adultsPagerState.currentPage + 1} ADULTS, ${childrenPagerState.currentPage + 1} CHILDREN",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SpaceLarge * 1.25f),
                    textAlign = TextAlign.Center,
                )
            },
            cardHeader = {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = SpaceLarge),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "SEATS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (seatsCardEnabled) MaterialTheme.colors.onSurface else ColorOnSurfaceVariant,
                    )

                    if (seatsCardEnabled) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                        )
                    }
                }
            },
            onTap = {
                setExpandedCardIndex(1)
            })

        StackItemCard(expandedIndex = expandedCardIndex,
            maxHeight = maxHeight,
            totalHeight = maxHeight * 0.68f,
            cardCollapsedColor = MaterialTheme.colors.surface,
            cardExpandedBgColor = MaterialTheme.colors.background,
            index = 2,
            cardExpandContent = {
                PayExpanded()
            },
            cardCollapseContent = {},
            cardHeader = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.logo), contentDescription = null)
                    Text(
                        text = "PAY",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = ColorOnSurfaceVariant,
                        modifier = Modifier.padding(start = SpaceSmall)
                    )
                }
            },
            onTap = {
                setExpandedCardIndex(2)
            })
    }
}