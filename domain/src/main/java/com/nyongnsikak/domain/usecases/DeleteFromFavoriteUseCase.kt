package com.nyongnsikak.domain.usecases

import com.nyongnsikak.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): FlowUseCase<Unit, Int>() {
    override fun buildFlowUseCase(params: Int?): Flow<Unit> {
        return  movieRepository.deleteFromFavorite(params!!)
    }
}