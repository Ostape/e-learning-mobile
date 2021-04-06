package com.robosh.basestartapplication.courses.view.subscribe

import android.view.View
import com.robosh.basestartapplication.model.Movie

interface SubscribeCourseClickListenerFactory  {

    fun createOnClickListener(movie: Movie): View.OnClickListener
}