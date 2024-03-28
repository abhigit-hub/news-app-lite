package com.compose.newsapplite.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)