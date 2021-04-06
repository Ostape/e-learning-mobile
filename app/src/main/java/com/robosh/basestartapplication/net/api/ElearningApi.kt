package com.robosh.basestartapplication.net.api

import com.robosh.basestartapplication.net.model.MovieListResponse
import com.robosh.basestartapplication.net.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ElearningApi {

    companion object {
        private const val API_KEY = "bf0af36b6b0149089510541e46af70f9"
        private const val API_KEY_QUERY = "?api_key=$API_KEY"

        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    @GET("movie/popular$API_KEY_QUERY")
    suspend fun getPopularMovieListReference(): Response<MovieListResponse>

    @GET("movie/{movie_id}$API_KEY_QUERY")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): Response<MovieResponse>
}