package com.robosh.basestartapplication.courses.view

import android.view.View
import com.robosh.basestartapplication.model.Movie

class CoursesClickListenerFactoryImpl constructor(
    private val callback: CoursesClickCallback
) : CoursesClickListenerFactory {

    override fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            callback.onCourseClicked(movie)
        }
    }
}