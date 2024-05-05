package com.compose.newsapplite.domain.repository

import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.presentation.model.NewsCategoryType
import com.compose.newsapplite.utils.Resource

interface NewsRepository {
    suspend fun getNewsByTrending(
        country: String = "in"
    ): Resource<NewsInfo>

    suspend fun getNewsByCategory(
        categoryType: NewsCategoryType = NewsCategoryType.TRENDING
    ): Resource<NewsInfo>
}