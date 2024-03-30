package com.compose.newsapplite.presentation.news.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.compose.newsapplite.ui.theme.NewsTypography

@Composable
fun NewsAppImageTextBox(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color,
    imageTintColor: Color,
    textAlign: TextAlign,
    isEnabled: Boolean = true,
    style: TextStyle
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = if (isEnabled) textColor else textColor.copy(alpha = 0.3f),
            textAlign = textAlign,
            style = style
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            imageVector = imageVector,
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier.height(150.dp),
            colorFilter = ColorFilter.tint(
                if (isEnabled) imageTintColor
                else imageTintColor.copy(alpha = 0.3f)
            )
        )
    }
}

@Composable
fun NewsAppTextBox(
    modifier: Modifier,
    text: String,
    textAlign: TextAlign,
    textColor: Color = Color.White,
    style: TextStyle = NewsTypography.headlineSmall
) {
    Box(
        modifier = modifier.padding(vertical = 5.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = textAlign,
            style = style
        )
    }
}

@Composable
fun NewsAppDateTextBox(
    modifier: Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = NewsTypography.titleMedium,
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier.padding(vertical = 5.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = textAlign,
            style = style
        )
    }
}

@Composable
fun NewsAppBackButton(
    modifier: Modifier,
    tintColor: Color = Color.White
) {
    Box(
        modifier = modifier.size(60.dp).padding(start = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier,
            colorFilter = ColorFilter.tint(tintColor)
        )
    }
}