package com.hackbyte.newsapplication.data.repository

import com.hackbyte.newsapplication.data.api.NewsApiService
import com.hackbyte.newsapplication.domain.models.NewsApiModel
import com.hackbyte.newsapplication.domain.repository.NewsApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsApiRepositoryImpl @Inject constructor(private val newsApiService: NewsApiService) :
    NewsApiRepository {

    override suspend fun topHeadlines(): NewsApiModel {
        return withContext(Dispatchers.IO) {
            newsApiService.topHeadlines()
        }
    }

    override suspend fun searchNews(query: String): NewsApiModel {
        return withContext(Dispatchers.IO) {
            newsApiService.searchNews(query)
        }
    }

}