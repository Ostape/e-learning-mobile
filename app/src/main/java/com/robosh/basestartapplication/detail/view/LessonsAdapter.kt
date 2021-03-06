package com.robosh.basestartapplication.detail.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.detail.view.lesson.ClickLessonListenerCallback
import com.robosh.basestartapplication.model.Lesson

class LessonsAdapter(
    private val clickLessonListenerCallback: ClickLessonListenerCallback
) : RecyclerView.Adapter<LessonsViewHolder>() {

    private val lessons = ArrayList<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        return LessonsViewHolder.create(parent, clickLessonListenerCallback)
    }

    override fun getItemCount(): Int = lessons.size

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(lessons[position])
    }

    fun setData(list: List<Lesson>) {
        lessons.clear()
        lessons.addAll(list)
        notifyDataSetChanged()
    }
}