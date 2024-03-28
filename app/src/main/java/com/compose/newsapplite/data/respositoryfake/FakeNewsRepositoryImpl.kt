package com.compose.newsapplite.data.respositoryfake

import android.util.Log
import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.db.NewsDAO
import com.compose.newsapplite.data.mapper.toNewsInfo
import com.compose.newsapplite.data.remote.NewsApi
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.repository.NewsRepository
import com.compose.newsapplite.utils.MockDataGenerator
import com.compose.newsapplite.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeNewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDAO: NewsDAO
): NewsRepository {

    @Inject lateinit var mockDataGenerator: MockDataGenerator

    companion object {
        private val TAG = FakeNewsRepositoryImpl::class.java.simpleName

        const val FILE_NAME_NEWS_BY_TRENDING_MOCK_DATA = "newsbytrending.json"
        const val FILE_NAME_NEWS_BY_CATEGORY_MOCK_DATA = "newsbycategory.json"
    }

    override suspend fun getNewsByTrending(country: String): Resource<NewsInfo> {
        Log.d(TAG, "MOCK REQ ==> getNewsByTrending() ==> (country = $country)")
        val mockData = mockDataGenerator.getMockDataFromAsset(FILE_NAME_NEWS_BY_TRENDING_MOCK_DATA)

        mockData?.let { newsDTO ->
            Log.d(TAG, "MOCK RESP <== getNewsByTrending() <== $newsDTO)")
            val newsInfo = newsDTO.toNewsInfo()
            return Resource.Success(data = newsInfo)
        }

        return Resource.Error(message = "getNewsByTrending() => Failed mock api request")
    }

    override suspend fun getNewsByCategory(category: String): Resource<NewsInfo> {
        Log.d(TAG, "MOCK REQ ==> getNewsByCategory() ==> (category = $category)")
        val mockData = mockDataGenerator.getMockDataFromAsset(FILE_NAME_NEWS_BY_CATEGORY_MOCK_DATA)

        mockData?.let { newsDTO ->
            Log.d(TAG, "MOCK RESP <== getNewsByCategory() <== $newsDTO)")
            val newsInfo = newsDTO.toNewsInfo()
            return Resource.Success(data = newsInfo)
        }

        return Resource.Error(message = "getNewsByCategory() => Failed mock api request")
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