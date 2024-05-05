package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.UserSelectionUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppDateTextBox
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.GraphicUtils
import com.compose.newsapplite.utils.toFormattedDateString

@Composable
fun CategorizedNewsContent(
    modifier: Modifier,
    categoryNewsUiState: CategoryNewsUiState,
    userSelectionUiState: UserSelectionUiState,
    onCategorySelected: (Int) -> Unit,
    onCategoryNewsItemClicked: (NewsArticleUiState) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CategoriesRowHeader(
            modifier = Modifier
                .fillMaxHeight(0.1f),
            userSelectionUiState = userSelectionUiState,
            onCategorySelected = onCategorySelected
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f))

        CategoriesContentArea(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth(),
            categoryNewsUiState = categoryNewsUiState,
            onCategoryNewsItemClicked = onCategoryNewsItemClicked
        )
    }
}

@Composable
fun CategoriesRowHeader(
    modifier: Modifier,
    userSelectionUiState: UserSelectionUiState,
    onCategorySelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(userSelectionUiState.allNewsCategoryTabList.size) {
            CategoryHeaderItem(
                modifier = modifier,
                headerItemName = userSelectionUiState.allNewsCategoryTabList[it].displayText,
                isSelected = it == userSelectionUiState.selectedCategoryIndex,
                onCategorySelected = {
                    onCategorySelected(it)
                }
            )
        }
    }
}

@Composable
fun CategoryHeaderItem(
    modifier: Modifier,
    headerItemName: String,
    isSelected: Boolean,
    onCategorySelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 20.dp)
            .fillMaxHeight()
            .clickable {
                onCategorySelected()
            }
    ) {
        NewsAppTextBox(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = headerItemName,
            textAlign = TextAlign.Start,
            style = if (isSelected) NewsTypography.titleLarge else NewsTypography.titleSmall,
            textColor = if (isSelected) Color.White else Color.LightGray.copy(alpha = 0.5f)
        )

        Box(
            modifier
                .height(5.dp)
                .width(40.dp)
                .background(color = if (isSelected) Color(0xFFFC8019) else Color.Transparent)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun CategoriesContentArea(
    modifier: Modifier,
    categoryNewsUiState: CategoryNewsUiState,
    onCategoryNewsItemClicked: (NewsArticleUiState) -> Unit
) {
    LazyColumn {
        items(categoryNewsUiState.categoryNews.size) { index ->
            CategoryNewsItem(
                modifier = modifier,
                newsArticleUiState = categoryNewsUiState.categoryNews[index],
                onCategoryNewsItemClicked = onCategoryNewsItemClicked
            )
        }
    }
}

@Composable
fun CategoryNewsItem(
    modifier: Modifier,
    newsArticleUiState: NewsArticleUiState,
    onCategoryNewsItemClicked: (NewsArticleUiState) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, start = 15.dp, end = 15.dp)
            .clickable {
                onCategoryNewsItemClicked(newsArticleUiState)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imagePainter = rememberImagePainter(
            data = newsArticleUiState.urlToImage
        )

        Image(
            painter = imagePainter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = GraphicUtils.getNewsAppColorFilter(),
            modifier = modifier
                .size(100.dp)
                .weight(0.25f)
                .clip(RoundedCornerShape(30.dp))
                .shadow(elevation = 50.dp)
                .blur(
                    radiusX = 1.5.dp,
                    radiusY = 1.5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
        )

        Column(
            modifier = modifier
                .weight(0.8f)
                .height(100.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            NewsAppTextBox(
                modifier = Modifier
                    .weight(0.55f),
                text = newsArticleUiState.title,
                textAlign = TextAlign.Start,
                style = NewsTypography.bodyLarge,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.45f)
            ) {
                NewsAppDateTextBox(
                    modifier = Modifier,
                    text = newsArticleUiState.author.take(20),
                    textColor = Color.LightGray,
                    style = NewsTypography.labelLarge
                )

                Spacer(modifier = Modifier.size(10.dp))
                Spacer(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(RoundedCornerShape(size = 50.dp))
                        .background(Color(0xFFFC8019))
                )
                Spacer(modifier = Modifier.size(10.dp))

                NewsAppDateTextBox(
                    modifier = Modifier,
                    text = newsArticleUiState.publishedAt.toFormattedDateString(),
                    textColor = Color.LightGray,
                    style = NewsTypography.labelLarge
                )
            }
        }
    }
}