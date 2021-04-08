package com.robosh.basestartapplication.detail.view.lesson

import android.view.View
import com.robosh.basestartapplication.model.Lesson

class ClickLessonListenerCallbackImpl(
    private val clickLessonCallback: ClickLessonCallback
) : ClickLessonListenerCallback {

    override fun createOnClickListener(lesson: Lesson): View.OnClickListener {
        return View.OnClickListener {
            clickLessonCallback.onLessonClicked(lesson)
        }
    }
}