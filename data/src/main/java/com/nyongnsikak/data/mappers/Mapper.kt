package com.nyongnsikak.data.mappers



interface Mapper<I, O> {
    fun mapToDomainLayer(data: I): O

}
