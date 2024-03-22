package com.compose.newsapplite.data.mapper

import com.compose.newsapplite.data.remote.dto.ArticleDTO
import com.compose.newsapplite.data.remote.dto.NewsDTO
import com.compose.newsapplite.data.remote.dto.SourceDTO
import com.compose.newsapplite.domain.model.ArticleInfo
import com.compose.newsapplite.domain.model.NewsInfo
import com.compose.newsapplite.domain.model.SourceInfo

fun NewsDTO.toNewsInfo(): NewsInfo {
    return NewsInfo(
        status = status,
        totalResults = totalResults,
        articles = articlesDTO.toArticleInfo()
    )
}

/*
* If checkIfAnySubItemsAreNull() returns
* true  -> skip that item
* false -> consider that item
* */
fun List<ArticleDTO>.toArticleInfo(): List<ArticleInfo> {
    return this
        .filter { checkIfAnySubItemsAreNull(it).not() }
        .map {
            ArticleInfo(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                source = it.sourceDTO.toSourceInfo(),
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
        .toList()
}

private fun checkIfAnySubItemsAreNull(it: ArticleDTO): Boolean {
    val isArticleDTOSubItemsNull = it.author == null || it.description == null ||
            it.publishedAt == null || it.sourceDTO == null || it.title == null ||
            it.url == null || it.urlToImage == null || it.content == null

    val isSourceDTOSubItemsNull = (it.sourceDTO == null || it.sourceDTO.id == null || it.sourceDTO.name == null)

    return isArticleDTOSubItemsNull || isSourceDTOSubItemsNull
}

fun SourceDTO.toSourceInfo(): SourceInfo {
    return SourceInfo(
        id = id,
        name = name
    )
}