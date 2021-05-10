package com.robosh.basestartapplication.net.usecase

import android.util.Log
import com.robosh.basestartapplication.model.CourseState
import com.robosh.basestartapplication.net.repository.CoursesRepository
import com.robosh.basestartapplication.net.repository.ElearningApiRepository
import javax.inject.Inject

class GetCoursesUseCaseImpl @Inject constructor(
    private val elearningApiRepository: ElearningApiRepository,
    private val coursesRepository: CoursesRepository
) : GetCoursesUseCase {

    override suspend fun execute(): CourseState {
        val movieListResponse = elearningApiRepository.getAllCourses()
        if (movieListResponse.isSuccessful) {
//            CourseState.DataListState(
//                movieMapper.map(movieListResponse.body()?.results ?: emptyList())
//            )
            val body = movieListResponse.body() ?: emptyList()
            Log.d("TAGGERR", body.toString())
        } else {
            CourseState.ErrorState("Some error occurred: ${movieListResponse.errorBody()}")
        }
        return CourseState.DataListState(coursesRepository.getCourseListReference())
    }
}