package com.compose.newsapplite.presentation.news.composables.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.presentation.news.composables.common.NewsAppDateTextBox
import com.compose.newsapplite.ui.theme.NewsTypography
import com.compose.newsapplite.utils.convertToNewsAppLiteDate
import java.time.LocalDate

@Composable
fun NewsTopBar(
    modifier: Modifier,
    userUiState: UserUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        val annotatedString = buildAnnotatedString {
            append("Hey, ")
            withStyle(
                style = SpanStyle(
                    Color(0xFFFC8019),
                    fontWeight = FontWeight.Light,
                ),
            ) {
                append(userUiState.userName)
            }
        }

        Text(
            text = annotatedString,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            style = NewsTypography.headlineLarge
        )

        Spacer(modifier = Modifier.size(5.dp))

        NewsAppDateTextBox(
            text = LocalDate.now().convertToNewsAppLiteDate(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.size(15.dp))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray
        )
    }
}