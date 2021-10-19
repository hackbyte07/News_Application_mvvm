package com.hackbyte.newsapplication.domain.usecase

import com.hackbyte.newsapplication.domain.Response
import com.hackbyte.newsapplication.domain.models.NewsApiModel
import com.hackbyte.newsapplication.domain.repository.NewsApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopHeadlines @Inject constructor(private val newsApiRepository: NewsApiRepository) {

    suspend operator fun invoke(): Flow<Response<NewsApiModel>> {
        return flow {
            try {
                emit(Response.Loading())
                val news = newsApiRepository.topHeadlines()
                emit(Response.Success(news))
            } catch (e: HttpException) {
                emit(Response.Error(e.message()))
            } catch (e: IOException) {
                emit(Response.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
    }

}