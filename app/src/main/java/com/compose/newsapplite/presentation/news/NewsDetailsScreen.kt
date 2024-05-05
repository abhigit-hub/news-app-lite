package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppBackButton
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.GraphicUtils
import com.compose.newsapplite.utils.toPublishedAtString
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsDetailsContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator
) {
    val selectedArticleUiState = viewModel.selectedArticleUiState.value

    if (selectedArticleUiState == null) {
        NewsAppDetailError()
    } else {
        NewsAppDetailsContent(selectedArticleUiState, destinationsNavigator)
    }
}

@Composable
private fun NewsAppDetailsContent(
    selectedArticleUiState: NewsArticleUiState,
    destinationsNavigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        val imagePainter = rememberImagePainter(
            data = selectedArticleUiState.urlToImage
        )

        NewsAppBackButton(
            modifier = Modifier
                .weight(0.07f)
                .fillMaxWidth()
                .background(Color.Black)
                .clickable {
                    destinationsNavigator.popBackStack()
                },
            containerAlignment = Alignment.BottomStart
        )

        Spacer(modifier = Modifier.height(20.dp))

        NewsAppTextBox(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 20.dp),
            text = selectedArticleUiState.title,
            textAlign = TextAlign.Start,
            style = NewsTypography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = imagePainter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = GraphicUtils.getNewsAppColorFilter(),
            modifier = Modifier
                .weight(0.18f)
                .fillMaxWidth()
                .blur(
                    radiusX = 1.5.dp,
                    radiusY = 1.5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                ),
        )

        NewsAppDetailMetaBanner(
            selectedArticleUiState = selectedArticleUiState,
            modifier = Modifier.weight(0.05f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        NewsContentBox(
            selectedArticleUiState = selectedArticleUiState,
            modifier = Modifier
                .weight(0.35f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
private fun NewsAppDetailMetaBanner(
    selectedArticleUiState: NewsArticleUiState,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color.DarkGray)
        )

        NewsAppTextBox(
            modifier = Modifier
                .weight(0.55f)
                .padding(horizontal = 10.dp),
            text = selectedArticleUiState.sourceName,
            textAlign = TextAlign.Start
        )

        NewsAppTextBox(
            modifier = Modifier
                .fillMaxWidth(0.25f),
            text = selectedArticleUiState.publishedAt.toPublishedAtString(),
            textAlign = TextAlign.End,
            containerAlignment = Alignment.CenterEnd,
            style = NewsTypography.titleMedium
        )
    }
}

@Composable
private fun NewsAppDetailError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        NewsAppTextBox(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Something went wrong! No content to show",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NewsContentBox(
    selectedArticleUiState: NewsArticleUiState,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        NewsAppTextBox(
            modifier = Modifier,
            text = selectedArticleUiState.content,
            textAlign = TextAlign.Start
        )
    }
}