package com.compose.newsapplite.presentation.news.composables.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.R
import com.compose.newsapplite.presentation.model.KeypadUiState
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.presentation.news.composables.landing.NewsButtonView
import com.compose.newsapplite.presentation.news.composables.landing.NewsFloatingActionButton
import com.compose.newsapplite.utils.KeypadConstants

@Composable
fun KeypadContainer(
    modifier: Modifier,
    keypadUiState: KeypadUiState,
    userUiState: UserUiState,
    onKeyboardVisible: (Boolean) -> Unit,
    onKeyChanged: (KeypadConstants.KeypadCharacter) -> Unit
) {
    if (keypadUiState.isKeypadVisible) {
        CustomKeypad(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Black),
            isCapsLockEnabled = keypadUiState.isCapsLockEnabled,
            onKeyChanged = { onKeyChanged(it) }
        )
    } else {
        CustomKeypadHidden(
            modifier = modifier,
            userUiState = userUiState,
            onKeyboardVisible = onKeyboardVisible
        )
    }
}

@Composable
private fun CustomKeypadHidden(
    modifier: Modifier,
    userUiState: UserUiState,
    onKeyboardVisible: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NewsButtonView(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .weight(0.8f)
                .padding(start = 75.dp),
            userUiState = userUiState,
            text = stringResource(id = R.string.landing_page_proceed)
        )

        NewsFloatingActionButton(
            modifier = Modifier.weight(0.2f),
            onKeyboardVisible = onKeyboardVisible
        )
    }
}