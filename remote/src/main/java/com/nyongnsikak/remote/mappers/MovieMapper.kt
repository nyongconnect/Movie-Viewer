package com.nyongnsikak.remote.mappers


import com.nyongnsikak.data.model.MovieData
import com.nyongnsikak.data.model.MovieItemData
import com.nyongnsikak.remote.model.MovieItemRemote
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieItemRemote, MovieItemData> {
    override fun mapToDataLayer(data: MovieItemRemote): MovieItemData {
        return with(data){
            MovieItemData(
                    page,
                    results = data.results.map {movieItem ->
                        MovieData(
                                adult = movieItem.adult,
                                backdrop_path = movieItem.backdrop_path,
                                genre_ids = movieItem.genre_ids,
                                id = movieItem.id,
                                original_language = movieItem.original_language,
                                original_title = movieItem.original_title,
                                overview = movieItem.overview,
                                popularity = movieItem.popularity,
                                poster_path = movieItem.poster_path,
                                release_date = movieItem.release_date,
                                video = movieItem.video,
                                vote_average = movieItem.vote_average,
                                vote_count = movieItem.vote_count,
                                title = movieItem.title
                        )
                    },
                    total_results = total_results,
                    total_pages = total_pages


            )

        }
    }
}