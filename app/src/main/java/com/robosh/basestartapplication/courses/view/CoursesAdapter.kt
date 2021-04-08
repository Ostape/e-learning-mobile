package com.robosh.basestartapplication.courses.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.courses.view.detail.DetailCoursesClickListenerFactory
import com.robosh.basestartapplication.courses.view.subscribe.SubscribeCourseClickListenerFactory
import com.robosh.basestartapplication.model.Course

class CoursesAdapter(
    private val clickListenerDetail: DetailCoursesClickListenerFactory,
    private val subscribeCourseClickListenerFactory: SubscribeCourseClickListenerFactory
) : RecyclerView.Adapter<CoursesViewHolder>() {

    private val courses = ArrayList<Course>()

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

    fun setData(list: List<Course>) {
        courses.clear()
        courses.addAll(list)
        notifyDataSetChanged()
    }
}