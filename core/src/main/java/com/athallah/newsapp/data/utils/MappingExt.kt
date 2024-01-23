package com.athallah.newsapp.data.utils

import com.athallah.newsapp.data.datasource.remote.api.response.HeadlineResponse
import com.athallah.newsapp.data.datasource.remote.api.response.Source
import com.athallah.newsapp.data.model.ArticlesItem


fun HeadlineResponse.toHeadline(): List<ArticlesItem> {
    return this.articles?.map {article ->
        ArticlesItem(
            article.source?.toSource() ?: ArticlesItem.Source("", ""),
            article.author ?: "",
            article.title ?: "",
            article.description ?: "",
            article.url ?: "",
            article.urlToImage ?: "",
            article.publishedAt ?: "",
            article.content ?: ""
        )
    } ?: emptyList()
}


private fun Source.toSource(): ArticlesItem.Source =
    ArticlesItem.Source(
        this.id ?: "",
        this.name ?: ""
    )
