package com.nyongnsikak.movieviewer.mapper

import com.nyongnsikak.domain.model.MovieItemDomain
import com.nyongnsikak.movieviewer.ui.movie.model.Movie
import com.nyongnsikak.movieviewer.ui.movie.model.MovieItem
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MovieItemDomain, Movie> {
    override fun mapToPresentationLayer(data: MovieItemDomain): Movie{
        return with(data){
            Movie(
                    page,
                    results = data.results.map {movieItem ->

                        MovieItem(
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
                    total_pages = total_pages)



        }

    }
}