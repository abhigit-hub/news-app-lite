package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.news.composables.main.MainContentArea
import com.compose.newsapplite.presentation.news.composables.main.NewsBottomBar
import com.compose.newsapplite.presentation.news.composables.main.NewsTopBar
import com.compose.newsapplite.presentation.news.destinations.NewsDetailsContainerDestination
import com.compose.newsapplite.presentation.news.destinations.NewsViewAllContainerDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsScreenContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator
) {
    val trendingNewsUiState = viewModel.trendingNewsUiState
    val categoryNewsUiState = viewModel.categoryNewsUiState
    val userUiState = viewModel.userUiState

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        NewsTopBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(0.12f),
            userUiState = userUiState.value
        )

        MainContentArea(
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(0.78f),
            trendingNewsUiState = trendingNewsUiState.value,
            categoryNewsUiState = categoryNewsUiState.value,
            onViewAllClicked = {
                if (trendingNewsUiState.value.trendingNews.isNotEmpty())
                    destinationsNavigator.navigate(
                        NewsViewAllContainerDestination()
                    )
            },
            onTrendingItemClicked = {
                viewModel.updateSelectedArticle(articleUiState = it)
                destinationsNavigator.navigate(
                    NewsDetailsContainerDestination()
                )
            },
            onSaveButtonClicked={
                viewModel.saveNews(it)
            }
        )

        NewsBottomBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(0.1f)
                .padding(20.dp)
        )
    }
}