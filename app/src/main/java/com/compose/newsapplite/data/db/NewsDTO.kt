package com.compose.newsapplite.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_db_table")
data class NewsDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String?,
    val content: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val sourceName: String
)
