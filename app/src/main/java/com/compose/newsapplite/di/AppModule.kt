package com.compose.newsapplite.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.compose.newsapplite.data.db.NewsDAO
import com.compose.newsapplite.data.db.NewsDatabase
import com.compose.newsapplite.data.remote.NewsApi
import com.compose.newsapplite.utils.MockDataGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsApi(okHttpClient: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NewsApi.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMockDataGenerator(@ApplicationContext appContext: Context): MockDataGenerator {
        return MockDataGenerator(appContext)
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(app: Application): NewsDatabase {
        return Room.databaseBuilder(app, NewsDatabase::class.java,"news_data_table")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDAO(newsDatabase: NewsDatabase): NewsDAO {
        return newsDatabase.getNewsDAO()
    }
}