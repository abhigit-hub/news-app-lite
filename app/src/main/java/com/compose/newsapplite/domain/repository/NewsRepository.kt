package com.compose.newsapplite.domain.repository

import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.remote.dto.ArticleDTO
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

    suspend fun saveNews(news: News)
    suspend fun deleteArticle(news: News)
    fun getSavedNews(): Flow<List<News>>
}