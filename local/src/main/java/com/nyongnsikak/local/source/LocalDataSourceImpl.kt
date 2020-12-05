package com.nyongnsikak.local.source

import com.nyongnsikak.data.model.MovieData
import com.nyongnsikak.data.source.LocalDataSource
import com.nyongnsikak.local.db.MovieDataBase
import com.nyongnsikak.local.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
        private val database : MovieDataBase,
        private val movieMapper: MovieMapper

): LocalDataSource
{
    override fun addToFavorite(movieData: MovieData): Flow<Unit> {
        return flow {
            emit(database.movieDao().addToFavorite(movieMapper.mapToLocal(movieData)))
        }
    }

    override fun getMoviesFromFavorite(): Flow<List<MovieData>> {
        return  database.movieDao().getMoviesFromFavorite().map {
            it.map {movieLocal ->
                movieMapper.mapToDataLayer(movieLocal)
            }
        }
    }

    override fun deleteFromFavorite(id: Int) {
       database.movieDao().deleteMovieFromFavorite(id)
    }
}