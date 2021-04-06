package com.robosh.basestartapplication.courses.view.subscribe

import android.view.View
import com.robosh.basestartapplication.model.Movie

class SubscribeCourseClickListenerFactoryImpl(
    private val subscribeCourseClickCallback: SubscribeCourseClickCallback
) : SubscribeCourseClickListenerFactory {

    override fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            subscribeCourseClickCallback.onSubscribeCourseClicked(movie)
        }
    }
}