package com.robosh.basestartapplication.net.di

import com.robosh.basestartapplication.net.usecase.*
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
    fun provideGetCoursesUseCase(getMoviesUseCaseImpl: GetMoviesUseCaseImpl): GetMoviesUseCase =
        getMoviesUseCaseImpl

    @Provides
    @ViewModelScoped
    fun provideGetOneCourseUseCase(getOneMovieUseCaseImpl: GetOneMovieUseCaseImpl): GetOneMovieUseCase =
        getOneMovieUseCaseImpl

    @Provides
    @ViewModelScoped
    fun provideAuthUseCase(getAuthUseCaseImpl: AuthUseCaseImpl): AuthUseCase = getAuthUseCaseImpl

    @Provides
    @ViewModelScoped
    fun provideGetUserDataUseCase(getUserDataUseCaseImpl: GetUserDataUseCaseImpl): GetUserDataUseCase =
        getUserDataUseCaseImpl
}
