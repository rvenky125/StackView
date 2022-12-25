package com.famas.stackviewexample.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.R
import com.famas.stackviewexample.getScreenSize
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceMedium
import com.famas.stackviewexample.ui.theme.SpaceSemiLarge
import com.famas.stackviewexample.ui.theme.SpaceSemiSmall
import com.famas.stackviewexample.ui.theme.SpaceSmall
import com.famas.stackviewexample.ui.theme.SpaceVerySmall

@Composable
fun ScheduleCalendarCollapse(
    selectedStartDateIndex: Int?,
    selectedEndDateIndex: Int?,
    selectedTimeChipIndex: Int?,
    modifier: Modifier = Modifier
) {
    val dates = remember {
        dates()
    }
    val size = getScreenSize()
    Column(modifier = modifier.height(size.height * 0.2f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
                .padding(top = SpaceLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.Top, modifier = Modifier
                    .weight(0.5f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.from),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(top = SpaceSmall)
                )
                Column(modifier = Modifier.padding(start = SpaceSemiLarge)) {
                    Text(text = buildAnnotatedString {
                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.onSurface,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        )
                        append("Lagos,")
                        pop()
                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        )
                        append(" LOS")
                    })
                    Text(text = "FROM NIGERIA", fontSize = 12.sp, color = ColorOnSurfaceVariant)
                }
            }

            Row(modifier = Modifier
                .padding(start = SpaceSemiSmall)
                .weight(0.5f)) {
                Image(
                    painter = painterResource(id = R.drawable.to),
                    contentDescription = null,
                    modifier = Modifier.padding(top = SpaceSemiSmall)
                )
                Column(modifier = Modifier.padding(start = SpaceVerySmall)) {
                    Text(text = buildAnnotatedString {
                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.onSurface,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        )
                        append("Santorini,")
                        pop()
                        pushStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp
                            )
                        )
                        append(" Chevok Port 2")
                    })
                    Text(text = "TO NEW OSGBO", fontSize = 12.sp, color = ColorOnSurfaceVariant)
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = size.width * 0.02f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge * 2f)
                .padding(top = SpaceSmall),
        ) {
            Text(text = buildAnnotatedString {
                pushStyle(
                    SpanStyle(
                        color = ColorOnSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                append("DATE: ")
                pop()
                pushStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                append("FEB ${dates[selectedStartDateIndex ?: 0].number} - FEB ${dates[selectedEndDateIndex ?: 0].number}")
            })

            Text(text = buildAnnotatedString {
                pushStyle(
                    SpanStyle(
                        color = ColorOnSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                append("TIME: ")
                pop()
                pushStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                append(timeSlots[selectedTimeChipIndex ?: 0])
            })
        }
    }
}