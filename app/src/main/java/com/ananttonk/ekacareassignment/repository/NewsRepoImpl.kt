package com.ananttonk.ekacareassignment.repository

import com.ananttonk.ekacareassignment.database.NewsDao
import com.ananttonk.ekacareassignment.model.News
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(private val newsApi: NewsAPIService, private val newsDao: NewsDao) :
    NewsRepository {
    override suspend fun getNews(apiKey: String): Result<News> = runCatching {
        newsApi.getNews(apiKey)
    }

    override suspend fun insertNewsArticle(newsArticle: News.Source) =
        newsDao.insertArticle(newsArticle)

    override fun getNewsArticleById(articleId: String): Flow<News.Source> =
        newsDao.getArticleById(articleId)

    override fun getAllSavedArticle(): Flow<List<News.Source>> = newsDao.getAllArticles()
    override suspend fun deleteArticle(articleId: String) = newsDao.deleteArticleById(articleId)
}