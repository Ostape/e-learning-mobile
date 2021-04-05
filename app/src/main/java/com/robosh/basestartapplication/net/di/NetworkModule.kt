package com.robosh.basestartapplication.net.di

import com.robosh.basestartapplication.net.RetrofitClientInstance
import com.robosh.basestartapplication.net.api.MovieDbApi
import com.robosh.basestartapplication.net.repository.MovieRepository
import com.robosh.basestartapplication.net.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() = RetrofitClientInstance.retrofitInstance

    @Singleton
    @Provides
    fun provideMovieDbApi(retrofit: Retrofit): MovieDbApi = retrofit.create(MovieDbApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository =
        movieRepositoryImpl
}
