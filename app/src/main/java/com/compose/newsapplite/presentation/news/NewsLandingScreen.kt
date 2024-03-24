package com.compose.newsapplite.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.presentation.news.composables.custom.KeypadContainer
import com.compose.newsapplite.presentation.news.composables.landing.LandingCaptureNameView
import com.compose.newsapplite.presentation.news.composables.landing.LandingMainView
import com.compose.newsapplite.presentation.news.destinations.NewsScreenContainerDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(true)
@Destination
@Composable
fun NewsLandingScreenContainer(
    viewModel: NewsViewModel,
    destinationsNavigator: DestinationsNavigator
) {
    val userUiState = viewModel.userUiState
    val keypadUiState = viewModel.keypadUiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LandingContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (keypadUiState.value.isKeypadVisible) 0.7f else 0.85f),
            userUiState = userUiState.value,
            onKeyboardVisible = {
                viewModel.handleKeypadVisibility(it)
            }
        )

        KeypadContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(if (keypadUiState.value.isKeypadVisible) 0.3f else 0.15f),
            keypadUiState = keypadUiState.value,
            userUiState = userUiState.value,
            onOnboardingComplete = {
                viewModel.updateUserName()
                destinationsNavigator.popBackStack()
                destinationsNavigator.navigate(
                   NewsScreenContainerDestination()
                )
            },
            onKeyboardVisible = {
                viewModel.handleKeypadVisibility(it)
            }
        ) {
            viewModel.handleOnKeyEvent(it)
        }
    }
}

@Composable
fun LandingContainer(
    userUiState: UserUiState,
    modifier: Modifier,
    onKeyboardVisible: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        LandingCaptureNameView(
            userUiState = userUiState,
            onKeyboardVisible = onKeyboardVisible
        )
        LandingMainView()
    }
}