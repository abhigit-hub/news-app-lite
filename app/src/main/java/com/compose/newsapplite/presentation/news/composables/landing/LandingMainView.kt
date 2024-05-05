package com.compose.newsapplite.presentation.news.composables.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.R
import com.compose.newsapplite.presentation.model.UserUiState
import com.compose.newsapplite.ui.theme.NewsTypography

@Composable
fun LandingMainView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        CarouselImageView()

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = stringResource(id = R.string.landing_page_title),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            style = NewsTypography.headlineLarge
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = stringResource(id = R.string.landing_page_subtitle),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            style = NewsTypography.titleMedium
        )
    }
}

@Composable
private fun CarouselImageView() {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.65f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_preview_bg1),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .padding(start = 15.dp)
                .clip(RoundedCornerShape(30.dp))
                .shadow(elevation = 50.dp)
                .align(Alignment.CenterStart)
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
        )
        Image(
            painter = painterResource(id = R.drawable.landing_preview_bg2),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .padding(end = 15.dp)
                .clip(RoundedCornerShape(30.dp))
                .shadow(elevation = 50.dp)
                .align(Alignment.CenterEnd)
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
        )
        Image(
            painter = painterResource(id = R.drawable.landing_preview_fg),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight(1f)
                .clip(RoundedCornerShape(30.dp))
        )
    }
}

@Composable
fun NewsButtonView(
    modifier: Modifier,
    userUiState: UserUiState,
    text: String,
    onOnboardingComplete: () -> Unit
) {
    Button(
        onClick = {
            onOnboardingComplete()
        },
        modifier = modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Color(0xFFFC8019),
                shape = RoundedCornerShape(20.dp)
            ),
        colors = ButtonColors(
            containerColor = Color(0x00000000),
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0x80000000),
            disabledContainerColor = Color(0x00FFFFFF),
        ),
        enabled = userUiState.hasUserEnteredValidName
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = if (userUiState.hasUserEnteredValidName) Color.White else Color.LightGray.copy(alpha = 0.1f),
            modifier = Modifier.fillMaxWidth(),
            style = NewsTypography.titleLarge
        )
    }
}

@Composable
fun NewsFloatingActionButton(
    modifier: Modifier,
    onKeyboardVisible: (Boolean) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        FloatingActionButton(
            onClick = { onKeyboardVisible(true) },
            containerColor = Color(0x00000000),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFFC8019),
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.vd_keypad),
                contentDescription = "",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}