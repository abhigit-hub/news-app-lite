package com.compose.newsapplite.utils

import android.content.Context
import com.compose.newsapplite.data.remote.dto.NewsDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockDataGenerator @Inject constructor(
    private val context: Context
) {
    private fun getJsonStringFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getMockDataFromAsset(fileName: String): NewsDTO? {
        val jsonFileString = getJsonStringFromAsset(fileName)

        jsonFileString?.also {
            val gson = Gson()
            val listPersonType = object : TypeToken<NewsDTO>() {}.type

            return gson.fromJson(jsonFileString, listPersonType)
        }

        return null
    }
}