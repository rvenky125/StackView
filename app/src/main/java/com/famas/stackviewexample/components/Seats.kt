package com.famas.stackviewexample.components

import android.graphics.pdf.PdfDocument.Page
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.famas.stackviewexample.ui.theme.SpaceLarge
import com.famas.stackviewexample.ui.theme.SpaceMedium
import com.famas.stackviewexample.ui.theme.SpaceSmall
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SeatsExpand() {
    val numbers = remember {
        (1..4).toList()
    }

    Column(modifier = Modifier.padding(vertical = SpaceLarge)) {
        Text(
            text = "How many adults?",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 29.sp,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "12 YEARS +",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpaceSmall),
            textAlign = TextAlign.Center
        )

        HorizontalPager(
            count = numbers.size,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = SpaceLarge * 7)
        ) { page ->
            Text(
                text = numbers[page].toString(),
                fontSize = 200.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.animatedPageOffsetModifier(page, this)
            )
        }

        HorizontalPager(
            count = numbers.size,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = SpaceLarge * 7)
        ) { page ->
            Text(
                text = numbers[page].toString(),
                fontSize = 200.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.animatedPageOffsetModifier(page, this)
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
fun Modifier.animatedPageOffsetModifier(page: Int, scope: PagerScope): Modifier {
    return graphicsLayer {
        with(scope) {
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

            // We animate the scaleX + scaleY, between 85% and 100%
            lerp(
                Dp(0.55f),
                Dp(1f),
                1f - pageOffset.coerceIn(0f, 1f)
            ).also { scale ->
                scaleX = scale.value
                scaleY = scale.value
            }

            // We animate the alpha, between 50% and 100%
            alpha = lerp(
                start = Dp(0.35f),
                stop = Dp(1f),
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            ).value
        }
    }
}