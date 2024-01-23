package com.athallah.newsapp.data.datasource.remote.api.service

import com.athallah.newsapp.data.datasource.remote.api.response.HeadlineResponse
import com.athallah.newsapp.data.model.News
import com.athallah.newsapp.data.utils.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "id",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<HeadlineResponse>

    @GET("v2/everything")
    fun getEverything(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<News>
}