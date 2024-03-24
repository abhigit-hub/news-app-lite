package com.compose.newsapplite.presentation.news.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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

@Composable
fun ImageWithText(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color,
    imageTintColor: Color,
    textAlign: TextAlign,
    style: TextStyle
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = textAlign,
            style = style
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            imageVector = imageVector,
            contentDescription = null, // Provide proper content description
            contentScale = ContentScale.None,
            modifier = Modifier.height(150.dp),
            colorFilter = ColorFilter.tint(imageTintColor)
        )
    }
}