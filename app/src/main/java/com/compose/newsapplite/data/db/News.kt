package com.compose.newsapplite.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.newsapplite.data.remote.dto.SourceDTO
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "news_data_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("author")
    val author: String?,
//    @SerializedName("content")
//    val content: String?,
//    @SerializedName("description")
//    val description: String,
//    @SerializedName("publishedAt")
//    val publishedAt: String,
//    @SerializedName("source")
//    val sourceDTO: SourceDTO,
//    @SerializedName("title")
//    val title: String,
//    @SerializedName("url")
//    val url: String,
//    @SerializedName("urlToImage")
//    val urlToImage: String?
):Serializable
