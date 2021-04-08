package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState

interface GetOneMovieUseCase {

    suspend fun execute(courseId: String): CourseState
}