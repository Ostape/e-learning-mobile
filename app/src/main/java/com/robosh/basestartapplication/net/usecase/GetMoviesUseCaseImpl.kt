package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.mapper.MovieMapper
import com.robosh.basestartapplication.net.repository.CoursesRepository
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val coursesRepository: CoursesRepository,
    private val movieMapper: MovieMapper
) : GetMoviesUseCase {

    override suspend fun execute(): CourseState {
        val movieListResponse = coursesRepository.getCourseListReference()
        return if (movieListResponse.isSuccessful) {
            CourseState.DataListState(
                movieMapper.map(movieListResponse.body()?.results ?: emptyList())
            )
        } else {
            CourseState.ErrorState("Some error occurred: ${movieListResponse.errorBody()}")
        }
    }
}