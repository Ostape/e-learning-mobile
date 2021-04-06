package com.robosh.basestartapplication.net.di

import com.robosh.basestartapplication.net.RetrofitClientInstance
import com.robosh.basestartapplication.net.api.ElearningApi
import com.robosh.basestartapplication.net.repository.MovieRepository
import com.robosh.basestartapplication.net.repository.CoursesRepositoryImpl
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
    fun provideMovieDbApi(retrofit: Retrofit): ElearningApi = retrofit.create(ElearningApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(coursesRepositoryImpl: CoursesRepositoryImpl): MovieRepository =
        coursesRepositoryImpl
}
