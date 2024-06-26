package com.compose.newsapplite.data.repository

import android.util.Log
import com.compose.newsapplite.data.mapper.toNewsInfo
import com.compose.newsapplite.data.remote.NewsApi
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.repository.NewsRepository
import com.compose.newsapplite.presentation.model.NewsCategoryType
import com.compose.newsapplite.utils.Resource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
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

    override suspend fun getNewsByCategory(categoryType: NewsCategoryType): Resource<NewsInfo> {
        return try {
            Log.d(TAG, "REQ ==> getNewsByCategory() ==> (categoryType = $categoryType)")
            val response = newsApi.getNewsByCategory(
                query = categoryType.searchKey
            )
            Log.d(TAG, "RESP <== getNewsByCategory() <== $response)")

            val data = response.toNewsInfo()
            Resource.Success(data = data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("getNewsByCategory() => Failed api request")
        }
    }
}