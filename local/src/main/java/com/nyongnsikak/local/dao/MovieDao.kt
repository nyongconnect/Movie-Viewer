package com.nyongnsikak.local.dao

import androidx.room.*
import com.nyongnsikak.local.model.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(movieLocal: MovieLocal)

    @Query("SELECT * FROM favorite")
    fun getMoviesFromFavorite(): Flow<List<MovieLocal>>

    @Query("DELETE FROM favorite WHERE id IS :id")
    fun deleteMovieFromFavorite(id: Int)

}