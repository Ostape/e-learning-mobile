package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.model.MovieListResponse
import com.robosh.basestartapplication.net.model.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovieListReference(): Response<MovieListResponse>

    suspend fun getMovieById(movieId: Int): Response<MovieResponse>
}