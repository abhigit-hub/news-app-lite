package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.data.remote.dto.SourceDTO

import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppImageTextBox
import com.compose.newsapplite.presentation.news.composables.common.NewsAppDateTextBox
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.GraphicUtils

import com.compose.newsapplite.utils.toFormattedDateString


@Composable
fun TrendingNewsContent(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    onViewAllClicked: () -> Unit,
    onTrendingItemClicked: (NewsArticleUiState) -> Unit,
    onSaveButtonClicked: (News) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TrendingNewsBanner(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            onViewAllClicked = onViewAllClicked,
            isTrendingListEmpty = trendingNewsUiState.trendingNews.isEmpty()
        )
        TrendingNewsLazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            trendingNewsUiState = trendingNewsUiState,
            onTrendingItemClicked = onTrendingItemClicked,
            onSaveButtonClicked = onSaveButtonClicked
        )
    }
}

@Composable
fun TrendingNewsBanner(
    modifier: Modifier,
    isTrendingListEmpty: Boolean,
    onViewAllClicked: () -> Unit
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

        NewsAppImageTextBox(
            imageVector = Icons.Default.KeyboardArrowRight,
            imageTintColor = Color(0xFFFC8019),
            text = "View all",
            textAlign = TextAlign.End,
            textColor = Color(0xFFFC8019),
            isEnabled = isTrendingListEmpty.not(),
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterEnd)
                .clickable {
                    if (isTrendingListEmpty.not()) onViewAllClicked()
                },
            style = NewsTypography.titleMedium
        )
    }
}

@Composable
fun TrendingNewsLazyRow(
    modifier: Modifier,
    trendingNewsUiState: TrendingNewsUiState,
    onTrendingItemClicked: (NewsArticleUiState) -> Unit,
    onSaveButtonClicked: (News) -> Unit
) {
    LazyRow {
        items(trendingNewsUiState.trendingNews.take(5).size) { index ->
            TrendingNewsItem(
                modifier = modifier,
                articleUiState = trendingNewsUiState.trendingNews[index],
                onTrendingItemClicked = onTrendingItemClicked,
                isForRow = true,
                onSaveButtonClicked = onSaveButtonClicked,
            )
        }
    }
}

@Composable
fun TrendingNewsItem(
    modifier: Modifier,
    articleUiState: NewsArticleUiState,
    onTrendingItemClicked: (NewsArticleUiState) -> Unit,
    isForRow: Boolean,
    onSaveButtonClicked: (News) -> Unit
) {
    Column(
        modifier = Modifier
            .height(if (isForRow) 280.dp else 400.dp)
            .width(if (isForRow) 320.dp else 400.dp)
            .padding(
                horizontal = if (isForRow) 15.dp else 0.dp,
                vertical = if (isForRow) 0.dp else 0.dp
            )
            .clickable {
                onTrendingItemClicked(articleUiState)
            },
        verticalArrangement = Arrangement.Bottom,
    ) {
        val imagePainter = rememberImagePainter(
            data = articleUiState.urlToImage
        )

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .weight(if (isForRow) 0.68f else 0.72f)

        ) {
            Image(
                painter = imagePainter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = GraphicUtils.getNewsAppColorFilter(),
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .shadow(elevation = 50.dp)
                    .blur(
                        radiusX = 1.5.dp,
                        radiusY = 1.5.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
            )

            NewsSaveOrDeleteButton(
                modifier = Modifier,
                onSaveButtonClicked = onSaveButtonClicked,
                articleUiState = articleUiState,
            )

        }

        NewsAppDateTextBox(
            modifier = Modifier.weight(if (isForRow) 0.1f else 0.1f),
            text = articleUiState.publishedAt.toFormattedDateString()
        )

        NewsAppTextBox(
            modifier = Modifier.weight(if (isForRow) 0.22f else 0.18f),
            text = articleUiState.title,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun NewsSaveOrDeleteButton(
    modifier: Modifier,
    onSaveButtonClicked: (News) -> Unit,
    articleUiState: NewsArticleUiState
) {
    IconButton(
        onClick = {
            onSaveButtonClicked(
                News(
                    author = articleUiState.author
                )
            )
        },
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            tint = Color.Red,
            modifier = modifier
                .size(48.dp)
                .offset(8.dp, 8.dp)
        )

    }
}
