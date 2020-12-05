package com.nyongnsikak.local.mapper


interface Mapper<I, O> {
        fun mapToDataLayer(data: I): O
        fun mapToLocal(data: O): I
}
