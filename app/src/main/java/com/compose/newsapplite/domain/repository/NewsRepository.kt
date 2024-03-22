package com.compose.newsapplite.domain.repository

import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.utils.Resource

interface NewsRepository {
    suspend fun getNewsByTrending(
        country: String = "in"
    ): Resource<NewsInfo>

    suspend fun getNewsByCategory(
        category: String = "random"
    ): Resource<NewsInfo>
}