package com.robosh.basestartapplication.model

sealed class CourseState {
    object Idle : CourseState()
    object LoadingState : CourseState()
    data class DataListState(val data: List<Movie>) : CourseState()
    data class ErrorState(val data: String) : CourseState()
    data class SingleDataState(val movie: Movie) : CourseState()
    data class CourseSubscribeClickedState(val movie: Movie) : CourseState()
    data class CourseDetailClickedState(val movie: Movie) : CourseState()
}