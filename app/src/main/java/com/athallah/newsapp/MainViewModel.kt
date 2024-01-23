package com.athallah.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athallah.newsapp.data.ResultState
import com.athallah.newsapp.data.model.ArticlesItem
import com.athallah.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _headlineState = MutableStateFlow<ResultState<List<ArticlesItem>>>(ResultState.Loading)
    val headlineState: StateFlow<ResultState<List<ArticlesItem>?>> = _headlineState

    fun getHeadline() {
        viewModelScope.launch {
            newsRepository.getHeadline().collect {
                _headlineState.value = it
            }
        }
    }
}