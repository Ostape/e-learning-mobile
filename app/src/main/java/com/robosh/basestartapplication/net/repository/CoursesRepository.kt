package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.model.MovieListResponse
import com.robosh.basestartapplication.net.model.MovieResponse
import retrofit2.Response

interface CoursesRepository {

    suspend fun getCourseListReference(): List<Course>

    suspend fun getCourseById(courseId: String): Course
}