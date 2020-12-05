package com.nyongnsikak.remote.source

import com.nyongnsikak.data.model.MovieItemData
import com.nyongnsikak.data.source.RemoteDataSource
import com.nyongnsikak.remote.api.MovieApi
import com.nyongnsikak.remote.mappers.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
   private var api : MovieApi,
   private var movieMapper: MovieMapper
) : RemoteDataSource {
    override fun getMovies(param : String?): Flow<MovieItemData> {
        return flow {
            val movieItems = api.getMovies(param)
            emit(
                movieMapper.mapToDataLayer(movieItems)

        )
        }
    }
}