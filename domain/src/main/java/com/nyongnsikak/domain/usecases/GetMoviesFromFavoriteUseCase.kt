package com.nyongnsikak.domain.usecases

import com.nyongnsikak.domain.model.MovieDomain
import com.nyongnsikak.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFromFavoriteUseCase @Inject constructor(
        private  val movieRepository: MovieRepository
): FlowUseCase<List<MovieDomain>, Unit>() {
    override fun buildFlowUseCase(params: Unit?): Flow<List<MovieDomain>> {
        return movieRepository.getMoviesFromFavorite()
    }
}