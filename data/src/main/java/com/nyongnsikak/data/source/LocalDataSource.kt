package com.nyongnsikak.data.source

import com.nyongnsikak.data.model.MovieData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {


    //favorite
    fun addToFavorite(movieData: MovieData): Flow<Unit>

    fun getMoviesFromFavorite(): Flow<List<MovieData>>

    fun deleteFromFavorite(id: Int)
}