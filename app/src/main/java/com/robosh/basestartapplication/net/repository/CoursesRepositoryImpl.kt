package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.api.ElearningApi
import com.robosh.basestartapplication.net.model.MovieListResponse
import com.robosh.basestartapplication.net.model.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : MovieRepository {

    private val coursesList = mutableListOf<Course>().apply {
//        add(Course("1", ""))
    }

    override suspend fun getMovieListReference(): Response<MovieListResponse> {
//        return
        return elearningApi.getPopularMovieListReference()
    }

    override suspend fun getMovieById(movieId: Int): Response<MovieResponse> {
        return elearningApi.getMovieById(movieId)
    }
}