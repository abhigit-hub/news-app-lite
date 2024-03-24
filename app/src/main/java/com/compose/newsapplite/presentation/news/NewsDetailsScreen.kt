package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.news.composables.common.NewsAppBackButton
import com.compose.newsapplite.presentation.news.composables.common.NewsAppTextBox
import com.compose.newsapplite.ui.theme.NewsTypography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsDetailsContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator
) {
    val selectedArticleUiState = viewModel.selectedArticleUiState

    if (selectedArticleUiState?.value == null) {
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
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(vertical = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            NewsAppTextBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .align(Alignment.TopCenter),
                text = selectedArticleUiState.value!!.title,
                textAlign = TextAlign.Center
            )

            NewsAppTextBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                text = selectedArticleUiState.value!!.content,
                textAlign = TextAlign.Center
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
                text = selectedArticleUiState.value!!.title,
                textAlign = TextAlign.Start,
                style = NewsTypography.titleLarge
            )
        }
    }
}