package com.compose.newsapplite.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news_data_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val content: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val sourceName: String
): Serializable
