package com.robosh.basestartapplication.detail.view.lesson

import android.view.View
import com.robosh.basestartapplication.model.Lesson

interface ClickLessonListenerCallback {

    fun createOnClickListener(lesson: Lesson): View.OnClickListener
}