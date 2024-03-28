package com.compose.newsapplite.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsDTO::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
