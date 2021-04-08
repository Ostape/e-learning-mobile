package com.robosh.basestartapplication.detail.view.lesson

import com.robosh.basestartapplication.model.Lesson

interface ClickLessonCallback {

    fun onLessonClicked(lesson: Lesson)
}