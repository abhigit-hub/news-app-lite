package com.compose.newsapplite.presentation.mapper

import com.compose.newsapplite.data.db.News
import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.domain.model.ArticleInfo
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.presentation.model.CategoryNewsUiState
import com.compose.newsapplite.presentation.model.NewsArticleUiState
import com.compose.newsapplite.presentation.model.TrendingNewsUiState

fun NewsInfo.toTrendingNewsUiState(): TrendingNewsUiState {
    return TrendingNewsUiState(
        trendingNews = this.articles.map { it.toNewsArticleUiState() }
    )
}

fun NewsInfo.toCategoryNewsUiState(): CategoryNewsUiState {
    return CategoryNewsUiState(
        categoryNews = this.articles.map { it.toNewsArticleUiState() }
    )
}

fun ArticleInfo.toNewsArticleUiState(): NewsArticleUiState {
    return NewsArticleUiState(
        author = this.author,
        content = this.content,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage,
        sourceName = this.source.name
    )
}

fun NewsArticleUiState.toNews(): News {
    return News(
        author = this.author,
        content = this.content,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage,
        sourceName = this.sourceName
    )
}