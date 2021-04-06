package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState

interface GetMoviesUseCase {

    suspend fun execute(): CourseState
}