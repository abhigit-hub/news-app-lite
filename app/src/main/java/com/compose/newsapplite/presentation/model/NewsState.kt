package com.compose.newsapplite.presentation.model

data class NewsUiState(
    val newsByTrendingSize: Int = 0,
    val newsByCategorySize: Int = 0,
)

data class KeypadUiState(
    val textPad: String = "",
    val isCapsLockEnabled: Boolean = true,
    val isKeypadVisible: Boolean = true
)

data class UserUiState(
    val userName: String = "READER",
    val hasUserEnteredValidName: Boolean = false
)