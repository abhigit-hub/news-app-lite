package com.compose.newsapplite.di

import com.compose.newsapplite.data.db.NewsDAO
import com.compose.newsapplite.data.repository.NewsRepositoryImpl
import com.compose.newsapplite.data.respositoryfake.FakeNewsRepositoryImpl
import com.compose.newsapplite.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

/*    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository*/

    @Binds
    @Singleton
    abstract fun bindFakeNewsRepository(
        fakeNewsRepositoryImpl: FakeNewsRepositoryImpl
    ): NewsRepository

}