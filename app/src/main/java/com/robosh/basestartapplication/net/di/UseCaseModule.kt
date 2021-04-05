package com.robosh.basestartapplication.net.di

import com.robosh.basestartapplication.net.usecase.GetMoviesUseCase
import com.robosh.basestartapplication.net.usecase.GetMoviesUseCaseImpl
import com.robosh.basestartapplication.net.usecase.GetOneMovieUseCase
import com.robosh.basestartapplication.net.usecase.GetOneMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetMoviesUseCase(getMoviesUseCaseImpl: GetMoviesUseCaseImpl): GetMoviesUseCase =
        getMoviesUseCaseImpl

    @Provides
    @ViewModelScoped
    fun provideGetOneMovieUseCase(getOneMovieUseCaseImpl: GetOneMovieUseCaseImpl): GetOneMovieUseCase =
        getOneMovieUseCaseImpl
}
