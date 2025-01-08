package com.ananttonk.ekacareassignment.module

import com.ananttonk.ekacareassignment.repository.NewsAPIService
import com.ananttonk.ekacareassignment.repository.NewsRepository

interface ApiService {
    val newsAPIService: NewsAPIService
    val newsRepo: NewsRepository
}