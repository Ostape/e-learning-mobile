package com.robosh.basestartapplication.detail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.databinding.ViewHolderCourseLessonBinding
import com.robosh.basestartapplication.model.Lesson
import com.squareup.picasso.Picasso

class LessonsViewHolder private constructor(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(
            parent: ViewGroup
        ): LessonsViewHolder {
            return LessonsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_course_lesson, parent, false)
            )
        }
    }

    private val binding: ViewHolderCourseLessonBinding = ViewHolderCourseLessonBinding.bind(view)

    fun bind(lesson: Lesson) {
        with(binding) {
            Picasso.get().load(lesson.imageLesson).into(imageLesson)
            lessonNumber.text = "Урок №${lesson.number}"
            lessonDuration.text = "${lesson.durationMinutes} хвилин"
            lessonDescription.text = lesson.text
        }
    }
}