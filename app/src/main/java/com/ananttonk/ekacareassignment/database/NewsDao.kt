package com.ananttonk.ekacareassignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ananttonk.ekacareassignment.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(news: News.Source)

    @Query("SELECT * FROM news")
    fun getAllArticles(): Flow<List<News.Source>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getArticleById(id: String): Flow<News.Source>

    @Query("DELETE FROM news WHERE id = :id")
    suspend fun deleteArticleById(id: String)

}