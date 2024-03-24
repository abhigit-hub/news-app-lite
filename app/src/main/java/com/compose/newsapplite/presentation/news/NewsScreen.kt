package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.presentation.news.composables.main.MainContentArea
import com.compose.newsapplite.presentation.news.composables.main.NewsBottomBar
import com.compose.newsapplite.presentation.news.composables.main.NewsTopBar
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.convertToNewsAppLiteDate
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate

@Destination
@Composable
fun NewsScreenContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator
) {
    val newsUiState = viewModel.newsUiState
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
        )

        NewsBottomBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(0.1f)
                .padding(20.dp)
        )
    }
}