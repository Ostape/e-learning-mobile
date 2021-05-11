package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.api.ElearningApi
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : CoursesRepository {


    override suspend fun getCourseListReference(): List<Course> {
        // elearningApi.getCourseListResponse
        return emptyList()
    }

    override suspend fun getCourseById(courseId: String): Course? {
//        return elearningApi.getMovieById(movieId)
        return null;
    }
}