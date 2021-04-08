package com.robosh.basestartapplication.courses.view.detail

import android.view.View
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Movie

class DetailCoursesClickListenerFactoryImpl constructor(
    private val callbackDetail: DetailCoursesClickCallback
) : DetailCoursesClickListenerFactory {

    override fun createOnClickListener(course: Course): View.OnClickListener {
        return View.OnClickListener {
            callbackDetail.onDetailCourseClicked(course)
        }
    }
}