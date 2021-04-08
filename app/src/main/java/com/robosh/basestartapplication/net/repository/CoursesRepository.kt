package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.model.MovieListResponse
import com.robosh.basestartapplication.net.model.MovieResponse
import retrofit2.Response

interface CoursesRepository {

    suspend fun getCourseListReference(): Response<MovieListResponse>

    suspend fun getCourseById(movieId: Int): Response<MovieResponse>
}