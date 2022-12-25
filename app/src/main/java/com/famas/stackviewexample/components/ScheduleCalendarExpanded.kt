package com.famas.stackviewexample.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.R
import com.famas.stackviewexample.ui.theme.ColorOnSurfaceVariant
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceMedium
import com.famas.stackviewexample.ui.theme.SpaceSemiLarge
import com.famas.stackviewexample.ui.theme.SpaceSemiSmall
import com.famas.stackviewexample.ui.theme.SpaceVerySmall

@Composable
fun ScheduleCalendarExpanded(
    selectedTimeChipIndex: Int?,
    selectedStartDateIndex: Int?,
    selectedEndDateIndex: Int?,
    setSelectedTimeChipIndex: (Int?) -> Unit,
    setSelectedStartDateIndex: (Int?) -> Unit,
    setSelectedEndDateIndex: (Int?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(horizontal = SpaceLarge * 1.5f, vertical = SpaceLarge)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                FromIcon(modifier = Modifier.padding(top = SpaceLarge))
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

            Row {
                Image(
                    painter = painterResource(id = R.drawable.to),
                    contentDescription = null,
                    modifier = Modifier.padding(top = SpaceSemiSmall)
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

        Divider(modifier = Modifier.padding(vertical = SpaceLarge))

        Text(
            text = "Dummy Calendar",
            fontSize = 24.0
                .sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(SpaceLarge))
        Calendar(
            selectedTimeChipIndex,
            selectedStartDateIndex,
            selectedEndDateIndex,
            setSelectedTimeChipIndex,
            setSelectedStartDateIndex,
            setSelectedEndDateIndex
        )
    }
}