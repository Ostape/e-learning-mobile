package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.mapper.CourseMapper
import com.robosh.basestartapplication.net.repository.ElearningApiRepository
import javax.inject.Inject

class GetCoursesUseCaseImpl @Inject constructor(
    private val elearningApiRepository: ElearningApiRepository,
    private val courseMapper: CourseMapper
) : GetCoursesUseCase {

    override suspend fun execute(): CourseState {
        val coursesResponse = elearningApiRepository.getAllCourses()
        return if (coursesResponse.isSuccessful) {
            CourseState.DataListState(
                courseMapper.map(coursesResponse.body() ?: emptyList())
            )
        } else {
            CourseState.ErrorState("Some error occurred: ${coursesResponse.errorBody()}")
        }
    }
}