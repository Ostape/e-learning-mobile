package com.robosh.basestartapplication.courses.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.model.Movie

class CoursesAdapter(
    private val clickListener: CoursesClickListenerFactory
) : RecyclerView.Adapter<CoursesViewHolder>() {

    private val courses = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder.create(
            parent,
            clickListener
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