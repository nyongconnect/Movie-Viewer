package com.nyongnsikak.domain.usecases

import com.nyongnsikak.domain.model.MovieItemDomain
import com.nyongnsikak.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : FlowUseCase<MovieItemDomain, String>() {

    override fun buildFlowUseCase(params: String?): Flow<MovieItemDomain> {
        return movieRepository.getMovies(params)
    }
}