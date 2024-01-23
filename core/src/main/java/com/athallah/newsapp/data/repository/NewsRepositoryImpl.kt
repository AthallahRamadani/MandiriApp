package com.athallah.newsapp.data.repository

import com.athallah.newsapp.data.ResultState
import com.athallah.newsapp.data.datasource.remote.api.service.ApiService
import com.athallah.newsapp.data.model.ArticlesItem
import com.athallah.newsapp.data.model.News
import com.athallah.newsapp.data.utils.toHeadline
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val apiService: ApiService
) : NewsRepository {
    override fun getHeadline(): Flow<ResultState<List<ArticlesItem>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getTopHeadlines()
            val data = response.body()?.toHeadline()
            if (data != null) {
                emit(ResultState.Success(data))
            }
        } catch (e: Exception){
            emit(ResultState.Error(e))
        }
    }
}
