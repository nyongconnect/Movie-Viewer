package com.nyongnsikak.movieviewer.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nyongnsikak.movieviewer.utils.AppConstants
import com.nyongnsikak.movieviewer.utils.AppConstants.API_KEY
import com.nyongnsikak.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {


    private val CONNECT_TIMEOUT = 30L
    private val READ_TIMEOUT = 30L
    private val WRITE_TIMEOUT = 30L

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .apply {

                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

                val httpLoggingInterceptor =
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                addInterceptor(httpLoggingInterceptor)

                addInterceptor { chain ->

                    /**
                     * append my api key to the network request
                     */

                    val url = chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter("api_key", API_KEY)

                    val newRequest = chain
                        .request()
                        .newBuilder()
                        .url(url.build())
                        .build()

                    chain.proceed(newRequest)

                }

            }

        return clientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}

