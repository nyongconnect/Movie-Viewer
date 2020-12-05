package com.nyongnsikak.domain.repository

import com.nyongnsikak.domain.model.MovieDomain
import com.nyongnsikak.domain.model.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    //remote
    fun getMovies(sortBy: String?) : Flow<MovieItemDomain>


    //local

    fun getMoviesFromFavorite() : Flow<List<MovieDomain>>
    fun addToFavorite(movieDomain: MovieDomain) : Flow<Unit>
    fun deleteFromFavorite(id : Int)  : Flow<Unit>

}