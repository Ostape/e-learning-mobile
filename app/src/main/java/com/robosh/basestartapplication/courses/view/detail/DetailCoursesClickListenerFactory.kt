package com.robosh.basestartapplication.courses.view.detail

import android.view.View
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Movie

interface DetailCoursesClickListenerFactory {

    fun createOnClickListener(course: Course): View.OnClickListener
}