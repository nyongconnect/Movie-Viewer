package com.nyongnsikak.data.source



import com.nyongnsikak.data.model.MovieItemData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {


    fun getMovies(param : String?) : Flow<MovieItemData>
}