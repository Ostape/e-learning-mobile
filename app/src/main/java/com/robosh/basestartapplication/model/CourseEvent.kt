package com.robosh.basestartapplication.model

sealed class CourseEvent {
    object CoursesIdle : CourseEvent()
    object CoursesFetch : CourseEvent()
    class CourseSubscribeClicked(val course: Course) : CourseEvent()
    class CourseDetailClicked(val course: Course) : CourseEvent()
    class CourseNotified(val id: String) : CourseEvent()
}