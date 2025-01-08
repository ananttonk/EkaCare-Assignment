package com.ananttonk.ekacareassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ananttonk.ekacareassignment.model.News
import com.ananttonk.ekacareassignment.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsList = MutableStateFlow<News?>(null)
    val newsList = _newsList.asStateFlow()

    private val _articleById = MutableStateFlow<News.Source?>(null)
    val articleById = _articleById.asStateFlow()

    private val _allSavedArticle = MutableStateFlow<List<News.Source>>(emptyList())
    val allSavedArticle = _allSavedArticle.asStateFlow()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNews().onSuccess {
                _newsList.value = it
            }.onFailure {
                Log.e("Error", "Error fetching news: ${it.message}")
            }
        }
    }

    fun insertNewsArticle(news: News.Source) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.insertNewsArticle(news)
        }
    }

    fun getArticleById(id: String) {
        viewModelScope.launch {
            newsRepository.getNewsArticleById(id).collect {
                _articleById.value = it
            }
        }
    }

    fun getAllSavedArticles() {
        viewModelScope.launch {
            newsRepository.getAllSavedArticle().collect {
                _allSavedArticle.value = it
            }
        }
    }

    fun deleteArticle(id: String) {
        viewModelScope.launch {
            newsRepository.deleteArticle(id)
        }
    }
}