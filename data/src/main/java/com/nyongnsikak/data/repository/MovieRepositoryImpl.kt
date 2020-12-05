package com.nyongnsikak.data.repository

import com.nyongnsikak.data.mappers.MovieMapper
import com.nyongnsikak.data.model.MovieData
import com.nyongnsikak.data.source.DataSourceFactory
import com.nyongnsikak.domain.model.MovieDomain
import com.nyongnsikak.domain.model.MovieItemDomain
import com.nyongnsikak.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (
        private val dataSourceFactory: DataSourceFactory,
        private var movieMapper : MovieMapper
) : MovieRepository {
    override fun getMovies(param : String?): Flow<MovieItemDomain> {
        return dataSourceFactory.remote().getMovies(param).map {
            movieitem-> movieMapper.mapToDomainLayer(movieitem)
        }
    }


    override fun getMoviesFromFavorite(): Flow<List<MovieDomain>> {
        return dataSourceFactory.local().getMoviesFromFavorite().map {
            it.map {
                with(it){
                    MovieDomain(
                        adult,
                            backdrop_path,
                            genre_ids,
                            id,
                            original_language,
                            original_title,
                            overview,
                            popularity,
                            poster_path,
                            release_date,
                            original_title,
                            video,
                            vote_average,
                            vote_count
                    )
                }
            }
        }
    }

    override fun addToFavorite(movieDomain: MovieDomain): Flow<Unit> {
        return dataSourceFactory.local().addToFavorite(
                with(movieDomain){
                    MovieData(
                            adult,
                            backdrop_path,
                            genre_ids,
                            id,
                            original_language,
                            original_title,
                            overview,
                            popularity,
                            poster_path,
                            release_date,
                            original_title,
                            video,
                            vote_average,
                            vote_count
                            )
                }
                )
    }

    override fun deleteFromFavorite(id: Int): Flow<Unit> {
        return flow {
            emit(dataSourceFactory.local().deleteFromFavorite(id))
        }
    }
}