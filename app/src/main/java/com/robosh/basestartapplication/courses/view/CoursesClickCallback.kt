package com.robosh.basestartapplication.courses.view

import com.robosh.basestartapplication.model.Movie

interface CoursesClickCallback {

    fun onCourseClicked(movie: Movie)
}