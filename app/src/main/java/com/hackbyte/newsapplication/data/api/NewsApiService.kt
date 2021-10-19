package com.hackbyte.newsapplication.data.api

import com.hackbyte.newsapplication.domain.models.NewsApiModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "53c34b96cf3147ff9035536974ecbf84"

interface NewsApiService {

    //top headlines
    @GET("v2/top-headlines?country=in&apiKey=$API_KEY")
    suspend fun topHeadlines(): NewsApiModel

    //search news
    @GET("v2/everything")
    suspend fun searchNews(@Query("q") query: String, @Query("apiKey") apiKey: String = API_KEY)
            : NewsApiModel


}