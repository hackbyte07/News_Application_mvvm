package com.hackbyte.newsapplication.di

import com.hackbyte.newsapplication.data.api.NewsApiBuilder
import com.hackbyte.newsapplication.data.api.NewsApiService
import com.hackbyte.newsapplication.data.repository.NewsApiRepositoryImpl
import com.hackbyte.newsapplication.domain.repository.NewsApiRepository
import com.hackbyte.newsapplication.domain.usecase.NewsUseCase
import com.hackbyte.newsapplication.domain.usecase.SearchNews
import com.hackbyte.newsapplication.domain.usecase.TopHeadlines
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsApiService(): NewsApiService {
        return NewsApiBuilder.instance()
    }

    @Singleton
    @Provides
    fun provideNewsApiRepository(newsApiService: NewsApiService): NewsApiRepository {
        return NewsApiRepositoryImpl(newsApiService)
    }

    @Singleton
    @Provides
    fun provideNewsUseCase(topHeadlines: TopHeadlines, searchNews: SearchNews)
            : NewsUseCase = NewsUseCase(topHeadlines, searchNews)

    @Singleton
    @Provides
    fun provideTopHeadlines(newsApiRepository: NewsApiRepository)
            : TopHeadlines = TopHeadlines(newsApiRepository)

    @Singleton
    @Provides
    fun provideSearchNews(newsApiRepository: NewsApiRepository)
            : SearchNews = SearchNews(newsApiRepository)
}