package com.ananttonk.ekacareassignment.repository

import com.ananttonk.ekacareassignment.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("top-headlines/sources")
    suspend fun getNews(@Query("apiKey") apiKey: String): News


}