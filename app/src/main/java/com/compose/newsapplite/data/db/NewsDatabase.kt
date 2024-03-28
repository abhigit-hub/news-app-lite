package com.compose.newsapplite.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.compose.newsapplite.data.remote.dto.ArticleDTO

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun getNewsDAO():NewsDAO
}