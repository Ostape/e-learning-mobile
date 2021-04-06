package com.robosh.basestartapplication.courses.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactory
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactory
import com.robosh.basestartapplication.databinding.ViewHolderCourseBinding
import com.robosh.basestartapplication.model.Movie
import com.robosh.basestartapplication.net.api.MovieDbApi.Companion.IMAGE_BASE_URL
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

    fun bind(movie: Movie) {
        with(binding) {
            Picasso.get().load(IMAGE_BASE_URL + movie.posterUrl).into(courseImageId)
            movieTitle.text = movie.title
            courseDescription.text = movie.description
            subscribeCourseButton.setOnClickListener(
                subscribeCourseClickListenerFactory.createOnClickListener(movie)
            )
            rating.rating = 4f
            courseViewHolderId.setOnClickListener(clickListenerDetail.createOnClickListener(movie))
        }
    }
}