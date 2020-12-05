package com.nyongnsikak.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nyongnsikak.local.dao.MovieDao
import com.nyongnsikak.local.model.MovieLocal


@Database(
        entities = [MovieLocal::class],
        version = 1,
        exportSchema = false
)
abstract class MovieDataBase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}

