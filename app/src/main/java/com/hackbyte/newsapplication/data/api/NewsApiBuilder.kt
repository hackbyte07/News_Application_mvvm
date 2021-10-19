package com.hackbyte.newsapplication.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NewsApiBuilder {

    private const val BASE_URL = "https://newsapi.org/"

    fun instance(): NewsApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(NewsApiService::class.java)
    }

}