package com.android.shopmax.local.typeConverters

import androidx.room.TypeConverter

class GenreIdTypeConverter {

    @TypeConverter
    fun toList(genreString: String): List<Int> {
        return genreString.split(" ").map {
            it.toInt() }
    }

    @TypeConverter
    fun toString(genre: List<Int>): String {

        var convertedString: String = " "

        genre.forEachIndexed { index, _ ->
            convertedString += genre[index].toString().plus(" ")

        }

        return convertedString.trim()
    }
}