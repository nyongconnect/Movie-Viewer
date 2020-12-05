package com.nyongnsikak.remote.api


import com.nyongnsikak.remote.model.MovieItemRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("4/discover/movie")
    suspend fun getMovies(@Query("sort_by") sortBy : String? = "popularity.desc") : MovieItemRemote
}