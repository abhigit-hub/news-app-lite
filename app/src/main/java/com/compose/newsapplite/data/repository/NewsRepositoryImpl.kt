package com.compose.newsapplite.data.repository

import android.util.Log
import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.db.NewsDAO
import com.compose.newsapplite.data.mapper.toNewsInfo
import com.compose.newsapplite.data.remote.NewsApi
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.repository.NewsRepository
import com.compose.newsapplite.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDAO: NewsDAO
): NewsRepository {

    companion object {
        private val TAG = NewsRepositoryImpl::class.java.simpleName
    }

    override suspend fun getNewsByTrending(country: String): Resource<NewsInfo> {
        return try {
            Log.d(TAG, "REQ ==> getNewsByTrending() ==> (country = $country)")
            val response = newsApi.getNewsByTrending(
                country = country
            )
            Log.d(TAG, "RESP <== getNewsByTrending() <== $response)")

            val data = response.toNewsInfo()
            Resource.Success(data = data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("getNewsByTrending() => Failed api request")
        }
    }

    override suspend fun getNewsByCategory(category: String): Resource<NewsInfo> {
        return try {
            Log.d(TAG, "REQ ==> getNewsByCategory() ==> (category = $category)")
            val response = newsApi.getNewsByCategory(
                query = category
            )
            Log.d(TAG, "RESP <== getNewsByCategory() <== $response)")

            val data = response.toNewsInfo()
            Resource.Success(data = data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("getNewsByCategory() => Failed api request")
        }
    }

    override suspend fun saveNews(news: News) {
        return newsDAO.insert(news)

    }

    override suspend fun deleteArticle(news: News) {
        return newsDAO.deleteNews(news)
    }

    override fun getSavedNews(): Flow<List<News>> {
        return newsDAO.getAllNews()
    }
}