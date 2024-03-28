package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.data.db.NewsDTO
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState

@Composable
fun MainContentArea(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    categoryNewsUiState: CategoryNewsUiState,
    onViewAllClicked: () -> Unit,
    onTrendingItemClicked: (NewsArticleUiState) -> Unit,
    onSaveOrDeleteNewsButtonClicked: (NewsDTO) -> Unit
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
            trendingNewsUiState = trendingNewsUiState,
            onViewAllClicked = onViewAllClicked,
            onTrendingItemClicked = onTrendingItemClicked,
            onSaveOrDeleteNewsButtonClicked = onSaveOrDeleteNewsButtonClicked
        )
        CategorizedNewsContent(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f),
            categoryNewsUiState = categoryNewsUiState
        )
    }
}