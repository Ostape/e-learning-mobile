package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.mapper.MovieMapper
import com.robosh.basestartapplication.net.repository.MovieRepository
import javax.inject.Inject

class GetOneMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : GetOneMovieUseCase {

    override suspend fun execute(movieId: Int): CourseState {
        val movieResponse = movieRepository.getMovieById(movieId)
        return if (movieResponse.isSuccessful) {
            movieResponse.body()?.let {
                CourseState.SingleDataState(movieMapper.map(listOf(it)).first())
            } ?: CourseState.ErrorState("Movie response is null")
        } else CourseState.ErrorState("Some error occurred: ${movieResponse.errorBody()}")
    }
}