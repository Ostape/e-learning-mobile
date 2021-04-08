package com.robosh.basestartapplication.model

sealed class CourseState {
    object Idle : CourseState()
    object LoadingState : CourseState()
    data class DataListState(val data: List<Course>) : CourseState()
    data class ErrorState(val data: String) : CourseState()
    data class SingleDataState(val course: Course) : CourseState()
    data class CourseSubscribeClickedState(val course: Course) : CourseState()
    data class CourseDetailClickedState(val course: Course) : CourseState()
}