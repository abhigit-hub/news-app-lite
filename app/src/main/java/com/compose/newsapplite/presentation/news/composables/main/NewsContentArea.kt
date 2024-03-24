package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState

@Composable
fun MainContentArea(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    categoryNewsUiState: CategoryNewsUiState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        TrendingNewsContent(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f),
            trendingNewsUiState = trendingNewsUiState
        )
        CategorizedNewsContent(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f),
            categoryNewsUiState = categoryNewsUiState
        )
    }
}