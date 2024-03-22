package com.compose.newsapplite.data.repository

import android.util.Log
import com.compose.newsapplite.data.mapper.toNewsInfo
import com.compose.newsapplite.data.remote.NewsApi
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.repository.NewsRepository
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

    override suspend fun getTopHeadlineNews(country: String): Resource<NewsInfo> {
        return try {
            Log.d(TAG, "REQ ==> getTopHeadlineNews() ==> (country = $country)")
            val response = newsApi.getTopHeadlineNews(country)
            Log.d(TAG, "RESP <== getTopHeadlineNews() <== $response)")

            val data = response.toNewsInfo()
            Resource.Success(data = data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Failed api request")
        }
    }
}