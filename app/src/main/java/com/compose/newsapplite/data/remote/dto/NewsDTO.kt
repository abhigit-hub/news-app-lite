package com.compose.newsapplite.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    val status: String,
    val totalResults: Int,
    @SerializedName("articles") val articlesDTO: List<ArticleDTO>
)

data class ArticleDTO(
    val author: String?,
    val content: String?,
    val description: String,
    val publishedAt: String,
    @SerializedName("source") val sourceDTO: SourceDTO,
    val title: String,
    val url: String,
    val urlToImage: String?
)

data class SourceDTO(
    val id: String,
    val name: String
)