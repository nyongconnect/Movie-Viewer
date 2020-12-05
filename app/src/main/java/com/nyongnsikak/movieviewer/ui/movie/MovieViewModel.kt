package com.nyongnsikak.movieviewer.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyongnsikak.domain.model.MovieDomain
import com.nyongnsikak.domain.usecases.AddToFavoriteUseCase
import com.nyongnsikak.domain.usecases.DeleteFromFavoriteUseCase
import com.nyongnsikak.domain.usecases.GetMoviesFromFavoriteUseCase
import com.nyongnsikak.domain.usecases.GetMoviesUseCase
import com.nyongnsikak.movieviewer.mapper.MovieMapper
import com.nyongnsikak.movieviewer.ui.movie.model.Movie
import com.nyongnsikak.movieviewer.ui.movie.model.MovieItem
import com.nyongnsikak.movieviewer.utils.Result
import com.nyongnsikak.movieviewer.utils.asLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @ViewModelInject constructor(
        private var getMoviesUseCase: GetMoviesUseCase,
        private var getMoviesFromFavoriteUseCase: GetMoviesFromFavoriteUseCase,
        private var addToFavoriteUseCase: AddToFavoriteUseCase,
        private var deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
        private val movieMapper: MovieMapper
) : ViewModel() {


    var gridCount : Int = 1

    private val _allMovies = MutableLiveData<Result<Movie>>()
    val movies: LiveData<Result<Movie>> = _allMovies.asLiveData()

    private val _favoriteMovies = MutableLiveData<Result<List<MovieItem>>>()
    val favoriteMovies = _favoriteMovies.asLiveData()

    private val _deleteMovies = MutableLiveData<Result<Nothing>>()
    val deleteMovies = _deleteMovies.asLiveData()

    private val _addToFav = MutableLiveData<Result<Nothing>>()
    val addToFav = _addToFav.asLiveData()


    var isAllMoviesSelected = true

    init {
        getFavoriteMovies()
        getAllMovies()
         }

    fun setColumn(isLinear: Boolean){
        gridCount = if (isLinear) 1
        else 2
    }


    fun getFavoriteMovies() {
        viewModelScope.launch {
            getMoviesFromFavoriteUseCase.execute()
                    .onStart { _favoriteMovies.postValue(Result.Loading) }
                    .catch { _favoriteMovies.postValue(Result.Error(it)) }
                    .collect {
                        _favoriteMovies.postValue(Result.Success(it.map {
                            with(it){
                                MovieItem(adult,
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
                                vote_count)
                            }

                        }))
                    }
        }
    }
    fun addToFavorite(movie: MovieItem) {
        viewModelScope.launch {
            addToFavoriteUseCase.execute(
                    with(movie) {
                        MovieDomain(
                                adult, backdrop_path, genre_ids, id, original_language, original_title, overview,
                                popularity, poster_path, release_date, title, video, vote_average, vote_count)
                    })
                    .onStart {_addToFav.postValue(Result.Loading) }
                    .catch { _addToFav.postValue(Result.Error(it))}
                    .collect {
                        _addToFav.postValue(Result.Success())
                    }
        }
    }

    fun removeFromFavorite(id: Int) {
        viewModelScope.launch {
            deleteFromFavoriteUseCase.execute(id)
                    .onStart { _deleteMovies.postValue(Result.Loading)}
                    .catch { _deleteMovies.postValue(Result.Error(it))}
                    .collect {
                        _deleteMovies.postValue(Result.Success())
                    }
        }

    }

    fun getAllMovies() {
        viewModelScope.launch {
            getMoviesUseCase.execute("popularity.desc")
                    .onStart { _allMovies.postValue(Result.Loading) }
                    .catch {
                        _allMovies.postValue(Result.Error(it))

                    }
                    .collect {
                        _allMovies.postValue(
                                Result.Success(movieMapper.mapToPresentationLayer(it))
                        )
                    }


        }
    }
}