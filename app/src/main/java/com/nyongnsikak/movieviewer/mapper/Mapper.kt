package com.nyongnsikak.movieviewer.mapper

interface Mapper<I, O> {
    fun mapToPresentationLayer(data: I): O
}
