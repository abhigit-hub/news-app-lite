package com.compose.newsapplite.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsDTO: NewsDTO)

    @Delete
    suspend fun deleteNews(newsDTO: NewsDTO)

    @Query("SELECT * FROM news_db_table")
    fun getAllNews(): Flow<List<NewsDTO>>
}