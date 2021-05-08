package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.Course

interface CoursesRepository {

    suspend fun getCourseListReference(): List<Course>

    suspend fun getCourseById(courseId: String): Course
}