package com.nyongnsikak.movieviewer.di

import android.content.Context
import androidx.room.Room
import com.nyongnsikak.data.source.LocalDataSource
import com.nyongnsikak.local.db.MovieDataBase
import com.nyongnsikak.local.source.LocalDataSourceImpl
import com.nyongnsikak.movieviewer.utils.AppConstants.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}


@Module
@InstallIn(ApplicationComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context) : MovieDataBase {

        return Room.databaseBuilder(
                context,
                MovieDataBase::class.java, DATABASE_NAME
        ).build()
    }
}