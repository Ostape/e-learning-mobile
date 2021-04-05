package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.MovieState

interface GetMoviesUseCase {

    suspend fun execute(): MovieState
}