package com.compose.newsapplite.utils

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

object GraphicUtils {
    fun getNewsAppColorFilter(): ColorFilter {
        return ColorFilter.tint(
            color = Color(0xFFFC8019).copy(alpha = 0.2f),
            blendMode = BlendMode.Darken
        )
    }
}