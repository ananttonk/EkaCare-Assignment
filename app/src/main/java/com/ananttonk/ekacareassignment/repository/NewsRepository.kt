package com.ananttonk.ekacareassignment.repository

import com.ananttonk.ekacareassignment.utils.Constant
import com.ananttonk.ekacareassignment.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(apiKey: String = Constant.API_KEY): Result<News>

    suspend fun insertNewsArticle(newsArticle: News.Source)

    fun getNewsArticleById(articleId: String): Flow<News.Source>

    fun getAllSavedArticle(): Flow<List<News.Source>>

    suspend fun deleteArticle(articleId: String)


}