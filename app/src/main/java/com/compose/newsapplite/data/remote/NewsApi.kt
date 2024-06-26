package com.compose.newsapplite.data.remote

import com.compose.newsapplite.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        const val BASE_URL_NEWS = "https://newsapi.org/v2/"
        const val NEWS_API_KEY = "b8ce9c8e490d4c4a960af31ec02edf44"
    }

    @GET("top-headlines")
    suspend fun getNewsByTrending(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsDTO

    @GET("everything")
    suspend fun getNewsByCategory(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "popular",
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsDTO
}