package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.MovieState

interface GetOneMovieUseCase {

    suspend fun execute(movieId: Int): MovieState
}