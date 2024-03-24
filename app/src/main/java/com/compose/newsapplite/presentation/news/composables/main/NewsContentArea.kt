package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
fun MainContentArea(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        TrendingNewsContent(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
        CategorizedNewsContent(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
    }
}