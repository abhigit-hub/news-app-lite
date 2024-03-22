package com.compose.newsapplite.data.remote

import com.compose.newsapplite.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        const val BASE_URL_NEWS = "https://newsapi.org/v2/"
        const val NEWS_API_KEY = "acad7614d6444a8bb3bc5339d4a2a9c4"
    }

    @GET("top-headlines")
    suspend fun getTopHeadlineNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsDTO
}