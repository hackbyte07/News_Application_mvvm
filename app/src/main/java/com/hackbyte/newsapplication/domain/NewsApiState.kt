package com.hackbyte.newsapplication.domain

import com.hackbyte.newsapplication.domain.models.NewsApiModel

data class NewsApiState(
    val isLoading: Boolean = false,
    val data: NewsApiModel = NewsApiModel(listOf(), "", 0),
    val error: String = ""
)
