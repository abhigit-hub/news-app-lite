package com.compose.newsapplite.presentation.news.composables.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.ui.theme.NewsTypography

@Composable
fun LandingCaptureNameView(
    userUiState: UserUiState,
    onKeyboardVisible: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        var userName = userUiState.userName
        val textColor = if (userUiState.hasUserEnteredValidName) Color(0xFFFC8019).copy(alpha = 0.8f) else Color(0xFFFC8019).copy(alpha = 0.3f)

        Text(
            text = " Hey, ",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f),
            style = NewsTypography.headlineLarge
        )
        Text(
            text = "$userName \uD83E\uDD20",
            textAlign = TextAlign.Center,
            color = textColor,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .clickable { onKeyboardVisible(true) },
            style = NewsTypography.displaySmall,
        )
        Spacer(modifier = Modifier.size(20.dp))
    }
}