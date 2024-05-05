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
    val allNewsCategoryTabList: List<NewsCategoryType> = NewsCategoryType.values().toList()
)

enum class NewsCategoryType(val displayText: String, val searchKey: String, val searchIndex: Int) {
    TRENDING(displayText = "trending", searchKey = "random", searchIndex = 0),
    SPORTS(displayText = "football", searchKey = "football", searchIndex = 1),
    POLITICS(displayText = "android", searchKey = "android", searchIndex = 2),
    TECHNOLOGY(displayText = "bangalore", searchKey = "bangalore", searchIndex = 3),
    GLOBAL_NEWS(displayText = "global", searchKey = "global|war", searchIndex = 4),
    FITNESS(displayText = "fitness", searchKey = "fitness", searchIndex = 5),
    MUSIC(displayText = "music", searchKey = "music", searchIndex = 6)
}