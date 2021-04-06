package com.robosh.basestartapplication.courses.view.subscribe

import com.robosh.basestartapplication.model.Movie

interface SubscribeCourseClickCallback {

    fun onSubscribeCourseClicked(course: Movie)
}