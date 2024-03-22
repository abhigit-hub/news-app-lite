package com.compose.newsapplite.domain.model

data class NewsInfo(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleInfo>
)

data class ArticleInfo(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceInfo,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class SourceInfo(
    val id: String,
    val name: String
)
