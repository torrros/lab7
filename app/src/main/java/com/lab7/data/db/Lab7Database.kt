package com.lab7.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lab7.data.dao.ArticleDao
import com.lab7.data.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}