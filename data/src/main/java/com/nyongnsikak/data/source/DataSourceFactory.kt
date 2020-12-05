package com.nyongnsikak.data.source

import javax.inject.Inject


class DataSourceFactory @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource

) {

    fun remote(): RemoteDataSource = remoteDataSource
    fun local(): LocalDataSource = localDataSource

}