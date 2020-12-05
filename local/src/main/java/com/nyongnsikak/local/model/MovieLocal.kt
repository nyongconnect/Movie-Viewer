package com.nyongnsikak.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.shopmax.local.typeConverters.GenreIdTypeConverter


@Entity(tableName = "favorite")
data class MovieLocal(
        @PrimaryKey
        val id: Int,
        val adult: Boolean,
        val backdrop_path: String,
        @field:TypeConverters(GenreIdTypeConverter::class)
        val genre_ids: List<Int>,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
)