package com.compose.newsapplite.data.db

import androidx.room.TypeConverter
import com.compose.newsapplite.data.remote.dto.SourceDTO


class Converters{
    @TypeConverter
    fun fromSource(source: SourceDTO):String?{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):SourceDTO{
        return SourceDTO(name,name)
    }

}