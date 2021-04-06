package com.robosh.basestartapplication.model

sealed class CourseEvent {
    object CoursesIdle : CourseEvent()
    object CoursesFetch : CourseEvent()
    class CourseSubscribeClicked(val movie: Movie) : CourseEvent()
    class CourseDetailClicked(val movie: Movie) : CourseEvent()
    class CourseNotified(val id: Int) : CourseEvent()
}