package com.compose.newsapplite.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Query("SELECT * FROM news_data_table")
    fun getAllNews(): Flow<List<News>>

    @Delete
    suspend fun deleteNews(news: News)
}