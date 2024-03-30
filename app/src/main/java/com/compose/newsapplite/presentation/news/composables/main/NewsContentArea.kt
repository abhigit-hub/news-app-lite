package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState
import com.compose.newsapplite.presentation.model.UserSelectionUiState

@Composable
fun MainContentArea(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    categoryNewsUiState: CategoryNewsUiState,
    userSelectionUiState: UserSelectionUiState,
    onViewAllClicked: () -> Unit,
    onTrendingNewsItemClicked: (NewsArticleUiState) -> Unit,
    onCategoryNewsItemClicked: (NewsArticleUiState) -> Unit,
    onCategorySelected: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        TrendingNewsContent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f),
            trendingNewsUiState = trendingNewsUiState,
            onViewAllClicked = onViewAllClicked,
            onTrendingNewsItemClicked = onTrendingNewsItemClicked
        )
        CategorizedNewsContent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f),
            categoryNewsUiState = categoryNewsUiState,
            userSelectionUiState = userSelectionUiState,
            onCategorySelected = onCategorySelected,
            onCategoryNewsItemClicked = onCategoryNewsItemClicked
        )
    }
}