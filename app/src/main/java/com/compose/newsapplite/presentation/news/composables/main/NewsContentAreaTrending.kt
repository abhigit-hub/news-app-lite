package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.news.composables.common.ImageWithText
import com.compose.newsapplite.ui.theme.NewsTypography

@Composable
fun TrendingNewsContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TrendingNewsBanner(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        )
        TrendingNewsLazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
        )
    }
}

@Composable
fun TrendingNewsBanner(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = "On Trending",
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterStart),
            style = NewsTypography.titleLarge
        )

        ImageWithText(
            imageVector = Icons.Default.KeyboardArrowRight,
            imageTintColor = Color(0xFFFC8019),
            text = "View all",
            textAlign = TextAlign.End,
            textColor = Color(0xFFFC8019),
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterEnd),
            style = NewsTypography.titleMedium
        )
    }
}

@Composable
fun TrendingNewsLazyRow(
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {

    }
}