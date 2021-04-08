package com.robosh.basestartapplication.net.mapper

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.model.MovieResponse
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun map(movieListResponse: List<MovieResponse>): List<Course> {
//        val resultMovies = mutableListOf<Movie>()
//        movieListResponse.forEach {
//            resultMovies.add(
//                Movie(
//                    it.id,
//                    it.title,
//                    it.description,
//                    it.posterPath,
//                    it.date
//                )
//            )
//        }
        return emptyList()
    }
}