package com.ananttonk.ekacareassignment

import android.app.Application
import com.ananttonk.ekacareassignment.database.NewsDatabase
import com.ananttonk.ekacareassignment.module.NewsModuleImpl

class NewsApplication : Application() {

    companion object {
        lateinit var instance: NewsApplication
    }

    private lateinit var newsModuleImpl: NewsModuleImpl

    override fun onCreate() {
        super.onCreate()
        instance = this
        val newsDatabase = NewsDatabase(this)
        newsModuleImpl = NewsModuleImpl(newsDatabase.getNewsDao())
    }

    fun getNewsModule() = newsModuleImpl

}