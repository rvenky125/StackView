package com.famas.stackviewexample.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.ColorSurfaceDisabled
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceMedium
import com.famas.stackviewexample.ui.theme.SpaceSemiLarge
import com.famas.stackviewexample.ui.theme.SpaceSemiSmall
import com.famas.stackviewexample.ui.theme.SpaceSmall

val dates = {
    val enabled = (1..29).toList().map { Date(enabled = true, it) }
    val disabled = (1..4).toList().map { Date(enabled = false, it) }
    listOf(Date(enabled = false, 30), Date(enabled = false, 31)).plus(enabled).plus(disabled)
}

val timeSlots = listOf("05:00 AM", "12:30 AM", "06:00 AM", "09:00 AM")

@Composable
fun Calendar(
    selectedTimeChipIndex: Int?, selectedStartDateIndex: Int?, selectedEndDateIndex: Int?,
    setSelectedTimeChipIndex: (Int?) -> Unit,
    setSelectedStartDateIndex: (Int?) -> Unit,
    setSelectedEndDateIndex: (Int?) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "JANUARY",
                    fontSize = 12.sp,
                    color = ColorOnSurfaceVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "FEBRUARY",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10f))
                        .background(MaterialTheme.colors.primary)
                        .padding(
                            SpaceSemiSmall
                        ),
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "MARCH", fontSize = 12.sp, color = ColorOnSurfaceVariant)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(SpaceSmall))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            itemsIndexed(dates()) { i, it ->
                val animatedBgColor = animateColorAsState(
                    targetValue = if (i in (selectedStartDateIndex ?: -1)..(selectedEndDateIndex
                            ?: selectedStartDateIndex ?: -1)
                    ) MaterialTheme.colors.primary else MaterialTheme.colors.background
                )

                val animatedCorner = animateDpAsState(
                    targetValue = if (i == selectedStartDateIndex || i == selectedEndDateIndex) SpaceLarge else 0.dp
                )

                Text(text = it.number.toString(),
                    color = if (it.enabled) MaterialTheme.colors.onSurface else ColorOnSurfaceVariant.copy(
                        alpha = 0.6f
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = SpaceSmall)
                        .clickable(it.enabled) {
                            if (selectedStartDateIndex == null) {
                                setSelectedStartDateIndex(i)
                            } else if (selectedEndDateIndex == null) {
                                setSelectedEndDateIndex(i)
                            } else {
                                setSelectedStartDateIndex(i)
                                setSelectedEndDateIndex(null)
                            }
                        }
                        .clip(
                            RoundedCornerShape(
                                topStart = if (selectedStartDateIndex == i) animatedCorner.value else 0.dp,
                                topEnd = if (selectedEndDateIndex == i) animatedCorner.value else 0.dp,
                                bottomEnd = if (selectedEndDateIndex == i) animatedCorner.value else 0.dp,
                                bottomStart = if (selectedStartDateIndex == i) animatedCorner.value else 0.dp
                            )
                        )
                        .background(color = animatedBgColor.value)
                        .padding(SpaceSemiSmall),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium)
            }
        }

        Text(
            text = "DEPARTURE TIME",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpaceSemiSmall),
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = SpaceMedium)
        ) {
            itemsIndexed(timeSlots) { i, str ->
                TimeChip(
                    text = str,
                    modifier = Modifier.padding(end = SpaceLarge),
                    enabled = selectedTimeChipIndex == i
                ) {
                    val index = if (selectedTimeChipIndex == i) {
                        null
                    } else i
                    setSelectedTimeChipIndex(index)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimeChip(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    onClick: () -> Unit,
) {
    Surface(
        color = if (enabled) MaterialTheme.colors.primary else ColorSurfaceDisabled,
        shape = RoundedCornerShape(
            SpaceSmall
        ),
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSurface,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(SpaceSemiLarge)
        )
    }
}

data class Date(
    val enabled: Boolean, val number: Int
)