package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppBackButton
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.presentation.news.composables.main.TrendingNewsItem
import com.compose.newsapplite.presentation.news.destinations.NewsDetailsContainerDestination
import com.compose.newsapplite.ui.theme.NewsTypography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsViewAllContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator,
) {

    val trendingNewsUiState = viewModel.trendingNewsUiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(vertical = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        TrendingNewsColumnRow(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(top = 60.dp),
            trendingNewsUiState = trendingNewsUiState.value,
            onTrendingItemClicked = {
                viewModel.updateSelectedArticle(articleUiState = it)
                destinationsNavigator.navigate(
                    NewsDetailsContainerDestination()
                )
            }
        )

        NewsAppBackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable {
                    destinationsNavigator.popBackStack()
                }
        )

        NewsAppTextBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 40.dp)
                .background(Color.Black)
                .align(Alignment.TopCenter),
            text = "Trending News",
            textAlign = TextAlign.Center,
            style = NewsTypography.titleLarge
        )
    }
}

@Composable
fun TrendingNewsColumnRow(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    onTrendingItemClicked: (NewsArticleUiState) -> Unit
) {
    LazyColumn {
        items(trendingNewsUiState.trendingNews.size) { index ->
            TrendingNewsItem(
                modifier = modifier,
                articleUiState = trendingNewsUiState.trendingNews[index],
                onTrendingItemClicked = onTrendingItemClicked,
                isForRow = false
            )
        }
    }
}