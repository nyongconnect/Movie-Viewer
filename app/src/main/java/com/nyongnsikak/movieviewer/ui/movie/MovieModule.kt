package com.nyongnsikak.movieviewer.ui.movie

import androidx.room.Index
import com.nyongnsikak.data.repository.MovieRepositoryImpl
import com.nyongnsikak.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun movieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}