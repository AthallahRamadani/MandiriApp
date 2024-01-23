package com.athallah.newsapp.data.repository

import com.athallah.newsapp.data.ResultState
import com.athallah.newsapp.data.model.ArticlesItem
import com.athallah.newsapp.data.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadline(): Flow<ResultState<List<ArticlesItem>>>
}