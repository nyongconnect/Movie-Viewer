package com.nyongnsikak.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<Result, in Param> {

    protected abstract fun buildFlowUseCase(params: Param? = null): Flow<Result>

    fun execute(params: Param? = null): Flow<Result> {
        return buildFlowUseCase(params).flowOn(Dispatchers.IO)
    }
}
