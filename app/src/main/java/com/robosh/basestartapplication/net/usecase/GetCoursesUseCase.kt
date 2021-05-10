package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState

interface GetCoursesUseCase {

    suspend fun execute(): CourseState
}