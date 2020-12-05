package com.nyongnsikak.movieviewer.ui.movie.viewholder

import android.view.View
import com.nyongnsikak.movieviewer.databinding.ItemMovieListBinding
import com.nyongnsikak.movieviewer.ui.movie.model.Movie
import com.nyongnsikak.movieviewer.ui.movie.model.MovieItem
import com.nyongnsikak.movieviewer.ui.zcustom.ViewHolder
import com.nyongnsikak.movieviewer.utils.AppConstants
import com.nyongnsikak.movieviewer.utils.loadImage

class MovieViewHolder (
    var binding : ItemMovieListBinding,
    private val movieItemClick: (MovieItem) -> Unit

): ViewHolder<MovieItem>(binding.root) {
    override fun bind(element: MovieItem) {
        binding.imageView.loadImage(AppConstants.BASE_IMAGE_URL + element.poster_path)
        with(itemView){
            setOnClickListener {
                movieItemClick(element)
            }
        }

    }
}