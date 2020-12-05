package com.nyongnsikak.local.mapper

import com.nyongnsikak.data.model.MovieData
import com.nyongnsikak.local.model.MovieLocal
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieLocal, MovieData> {
    override fun mapToDataLayer(data: MovieLocal): MovieData {
        return with(data){
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
                    title,
                    video,
                    vote_average,
                    vote_count
            )
        }
    }

    override fun mapToLocal(data: MovieData): MovieLocal {
        return with(data){
            MovieLocal(
                    id,
                    adult,
                    backdrop_path,
                    genre_ids,
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