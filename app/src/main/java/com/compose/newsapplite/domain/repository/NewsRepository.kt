package com.compose.newsapplite.domain.repository

import com.compose.newsapplite.data.db.NewsDTO
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsByTrending(
        country: String = "in"
    ): Resource<NewsInfo>

    suspend fun getNewsByCategory(
        category: String = "random"
    ): Resource<NewsInfo>

    suspend fun saveNews(newsDTO: NewsDTO)
    suspend fun deleteSavedNews(newsDTO: NewsDTO)
    fun getAllSavedNews(): Flow<List<NewsDTO>>
}