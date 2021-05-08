package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Lesson
import com.robosh.basestartapplication.net.api.ElearningApi
import com.robosh.basestartapplication.net.data.coursesList
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : CoursesRepository {


    override suspend fun getCourseListReference(): List<Course> {
        // elearningApi.getCourseListResponse
        return coursesList
    }

    override suspend fun getCourseById(courseId: String): Course {
//        return elearningApi.getMovieById(movieId)
        return coursesList.first { it.id == courseId }
    }
}