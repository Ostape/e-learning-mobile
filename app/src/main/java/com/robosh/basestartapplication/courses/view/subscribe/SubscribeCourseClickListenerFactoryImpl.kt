package com.robosh.basestartapplication.courses.view.subscribe

import android.view.View
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Movie

class SubscribeCourseClickListenerFactoryImpl(
    private val subscribeCourseClickCallback: SubscribeCourseClickCallback
) : SubscribeCourseClickListenerFactory {

    override fun createOnClickListener(course: Course): View.OnClickListener {
        return View.OnClickListener {
            subscribeCourseClickCallback.onSubscribeCourseClicked(course)
        }
    }
}