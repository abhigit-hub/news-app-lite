package com.compose.newsapplite.data.mapper

import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.data.remote.dto.NewsDTO
import com.compose.newsapplite.data.remote.dto.SourceDTO
import com.compose.newsapplite.domain.model.ArticleInfo
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.model.SourceInfo

private const val PLACEHOLDER_URL_TO_IMAGE = "https://cdn.arstechnica.net/wp-content/uploads/2024/03/apple-mchip-encryption-vulnerability-760x380.jpg"

fun NewsDTO.toNewsInfo(): NewsInfo {
    return NewsInfo(
        articles = articlesDTO.toArticleInfo()
    )
}

/*
* If checkIfSubItemsAreInvalid() returns
* true  -> skip that item
* false -> consider that item
* */
fun List<ArticleDTO>.toArticleInfo(): List<ArticleInfo> {
    return this
        .filter { checkIfSubItemsAreInvalid(it).not() }
        .map {
            ArticleInfo(
                author = it.author ?: it.sourceDTO.name,
                content = it.content!!,
                publishedAt = it.publishedAt,
                source = it.sourceDTO.toSourceInfo(),
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage ?: PLACEHOLDER_URL_TO_IMAGE
            )
        }
        .toList()
}

private fun checkIfSubItemsAreInvalid(it: ArticleDTO): Boolean {
    return it.content == null || it.content.contains("emoved")
            || it.title == null || it.title.contains("emoved")
}

fun SourceDTO.toSourceInfo(): SourceInfo {
    return SourceInfo(
        name = name
    )
}