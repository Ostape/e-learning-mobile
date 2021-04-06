package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.mapper.MovieMapper
import com.robosh.basestartapplication.net.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : GetMoviesUseCase {

    override suspend fun execute(): CourseState {
        val movieListResponse = movieRepository.getMovieListReference()
        return if (movieListResponse.isSuccessful) {
            CourseState.DataListState(
                movieMapper.map(movieListResponse.body()?.results ?: emptyList())
            )
        } else {
            CourseState.ErrorState("Some error occurred: ${movieListResponse.errorBody()}")
        }
    }
}