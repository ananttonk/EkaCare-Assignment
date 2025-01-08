package com.ananttonk.ekacareassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ananttonk.ekacareassignment.model.News

@Database(entities = [News.Source::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCES: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCES ?: synchronized(LOCK) {
            INSTANCES ?: createDataBase(context).also {
                INSTANCES = it
            }
        }

        private fun createDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "AyurvedaDatabase"
        ).fallbackToDestructiveMigration().build()
    }

}