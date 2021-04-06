package com.robosh.basestartapplication.courses.view.detail

import android.view.View
import com.robosh.basestartapplication.model.Movie

interface DetailCoursesClickListenerFactory {

    fun createOnClickListener(movie: Movie): View.OnClickListener
}