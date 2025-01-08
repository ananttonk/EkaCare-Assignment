package com.ananttonk.ekacareassignment.module

import com.ananttonk.ekacareassignment.utils.Constant
import com.ananttonk.ekacareassignment.database.NewsDao
import com.ananttonk.ekacareassignment.repository.NewsAPIService
import com.ananttonk.ekacareassignment.repository.NewsRepoImpl
import com.ananttonk.ekacareassignment.repository.NewsRepository

class NewsModuleImpl(newsDao:NewsDao) : ApiService {
    override val newsAPIService: NewsAPIService by lazy {
        RetrofitClient().getRetrofit(Constant.BASE_URL)
            .create(NewsAPIService::class.java)
    }
    override val newsRepo: NewsRepository by lazy {
        NewsRepoImpl(newsAPIService,newsDao)
    }
}