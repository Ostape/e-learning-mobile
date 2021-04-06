package com.robosh.basestartapplication.courses.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactory
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactory
import com.robosh.basestartapplication.model.Movie

class CoursesAdapter(
    private val clickListenerDetail: DetailCoursesClickListenerFactory,
    private val subscribeCourseClickListenerFactory: SubscribeCourseClickListenerFactory
) : RecyclerView.Adapter<CoursesViewHolder>() {

    private val courses = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder.create(
            parent,
            clickListenerDetail,
            subscribeCourseClickListenerFactory
        )
    }

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    fun setData(list: List<Movie>) {
        courses.clear()
        courses.addAll(list)
        notifyDataSetChanged()
    }
}