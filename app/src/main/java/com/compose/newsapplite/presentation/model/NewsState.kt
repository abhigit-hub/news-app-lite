package com.compose.newsapplite.presentation.model

data class TrendingNewsUiState(
    val trendingNews: List<NewsArticleUiState>
)

data class CategoryNewsUiState(
    val categoryNews: List<NewsArticleUiState>
)

data class NewsArticleUiState(
    val author: String,
    val content: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val sourceName: String
)

data class KeypadUiState(
    val textPad: String = "",
    val isCapsLockEnabled: Boolean = true,
    val isKeypadVisible: Boolean = true
)

data class UserUiState(
    val userName: String = "READER",
    val hasUserEnteredValidName: Boolean = false,
)

data class UserSelectionUiState(
    val selectedCategoryIndex: Int = 0,
    val allNewsCategoryTabList: List<String> = listOf("Trending", "Sports", "Politics", "Technology", "Global News", "Fitness", "Music")
)