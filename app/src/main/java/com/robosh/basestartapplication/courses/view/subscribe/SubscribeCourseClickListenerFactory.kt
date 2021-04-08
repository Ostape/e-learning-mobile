package com.robosh.basestartapplication.courses.view.subscribe

import android.view.View
import com.robosh.basestartapplication.model.Course

interface SubscribeCourseClickListenerFactory  {

    fun createOnClickListener(course: Course): View.OnClickListener
}