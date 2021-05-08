package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.mapper.UserMapper
import com.robosh.basestartapplication.net.repository.CoursesRepository
import javax.inject.Inject

class GetOneMovieUseCaseImpl @Inject constructor(
    private val coursesRepository: CoursesRepository,
    private val userMapper: UserMapper
) : GetOneMovieUseCase {

    override suspend fun execute(courseId: String): CourseState {
        val courseResponse = coursesRepository.getCourseById(courseId)
//        return if (movieResponse.isSuccessful) {
//            movieResponse.body()?.let {
//                CourseState.SingleDataState(movieMapper.map(listOf(it)).first())
//            } ?: CourseState.ErrorState("Movie response is null")
//        } else CourseState.ErrorState("Some error occurred: ${movieResponse.errorBody()}")
        return CourseState.SingleDataState(courseResponse)
    }
}