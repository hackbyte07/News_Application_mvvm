package com.hackbyte.newsapplication.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.newsapplication.domain.NewsApiState
import com.hackbyte.newsapplication.domain.Response
import com.hackbyte.newsapplication.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _topHeadlines = mutableStateOf(NewsApiState())
    val topHeadlines: State<NewsApiState> = _topHeadlines

    private val _searchNews = mutableStateOf(NewsApiState())
    val searchNews: State<NewsApiState> = _searchNews

    init {
        topHeadlines()
    }


    private fun topHeadlines() {
        viewModelScope.launch {
            newsUseCase.topHeadlines().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _topHeadlines.value = NewsApiState(isLoading = true)
                    }
                    is Response.Success -> {
                        _topHeadlines.value = NewsApiState(data = response.data!!)
                    }
                    is Response.Error -> {
                        _topHeadlines.value = NewsApiState(error = response.errorMessage!!)
                    }
                }
            }
        }
    }

    fun searchNews(query: String): Boolean {
        val value = query.trim()
        if (value.isEmpty() || value.isBlank()) return false
        viewModelScope.launch {
            newsUseCase.searchNews(query = value).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _searchNews.value = NewsApiState(isLoading = true)
                    }
                    is Response.Success -> {
                        _searchNews.value = NewsApiState(data = response.data!!)
                    }
                    is Response.Error -> {
                        _searchNews.value = NewsApiState(error = response.errorMessage!!)
                    }
                }
            }
        }
        return true
    }

}