package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.MovieState
import com.robosh.basestartapplication.net.mapper.MovieMapper
import com.robosh.basestartapplication.net.repository.MovieRepository
import javax.inject.Inject

class GetOneMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : GetOneMovieUseCase {

    override suspend fun execute(movieId: Int): MovieState {
        val movieResponse = movieRepository.getMovieById(movieId)
        return if (movieResponse.isSuccessful) {
            movieResponse.body()?.let {
                MovieState.SingleDataState(movieMapper.map(listOf(it)).first())
            } ?: MovieState.ErrorState("Movie response is null")
        } else MovieState.ErrorState("Some error occurred: ${movieResponse.errorBody()}")
    }
}