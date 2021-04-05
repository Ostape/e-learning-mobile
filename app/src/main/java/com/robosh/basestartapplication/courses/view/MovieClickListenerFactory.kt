package com.robosh.basestartapplication.courses.view

import android.view.View
import com.robosh.basestartapplication.model.Movie

interface MovieClickListenerFactory {

    fun createOnClickListener(movie: Movie): View.OnClickListener
}