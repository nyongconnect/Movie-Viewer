package com.nyongnsikak.domain.usecases

import com.nyongnsikak.domain.model.MovieDomain
import com.nyongnsikak.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : FlowUseCase<Unit, MovieDomain>() {
    override fun buildFlowUseCase(params: MovieDomain?): Flow<Unit> {
        return params?.let { movieRepository.addToFavorite(it) }!!
    }
}