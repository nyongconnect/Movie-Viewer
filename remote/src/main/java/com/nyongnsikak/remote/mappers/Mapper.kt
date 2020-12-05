package com.nyongnsikak.remote.mappers

interface Mapper<in R, D> {
    fun mapToDataLayer(data: R): D
}
