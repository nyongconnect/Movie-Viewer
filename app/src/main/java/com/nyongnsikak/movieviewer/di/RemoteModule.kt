package com.nyongnsikak.movieviewer.di

import com.nyongnsikak.data.source.RemoteDataSource
import com.nyongnsikak.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}