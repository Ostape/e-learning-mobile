package com.robosh.basestartapplication.courses.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactory
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactory
import com.robosh.basestartapplication.databinding.ViewHolderCourseBinding
import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.Movie
import com.robosh.basestartapplication.net.api.ElearningApi.Companion.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class CoursesViewHolder private constructor(
    view: View,
    private val clickListenerDetail: DetailCoursesClickListenerFactory,
    private val subscribeCourseClickListenerFactory: SubscribeCourseClickListenerFactory
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(
            parent: ViewGroup,
            clickListenerDetail: DetailCoursesClickListenerFactory,
            subscribeCourseClickListenerFactory: SubscribeCourseClickListenerFactory
        ): CoursesViewHolder {
            return CoursesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_course, parent, false),
                clickListenerDetail,
                subscribeCourseClickListenerFactory
            )
        }
    }

    private val binding: ViewHolderCourseBinding = ViewHolderCourseBinding.bind(view)

    fun bind(course: Course) {
        with(binding) {
            Picasso.get().load(course.courseImage).into(courseImageId)
            movieTitle.text = course.name
            courseDescription.text = course.description
            subscribeCourseButton.setOnClickListener(
                subscribeCourseClickListenerFactory.createOnClickListener(course)
            )
            rating.rating = 4f
            courseViewHolderId.setOnClickListener(clickListenerDetail.createOnClickListener(course))
        }
    }
}