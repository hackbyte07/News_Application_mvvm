package com.hackbyte.newsapplication.domain.repository

import com.hackbyte.newsapplication.domain.models.NewsApiModel

interface NewsApiRepository {

    suspend fun topHeadlines(): NewsApiModel

    suspend fun searchNews(query: String): NewsApiModel

}