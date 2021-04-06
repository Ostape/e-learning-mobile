package com.robosh.basestartapplication.model

import java.util.*

data class Lesson(
    val id: String,
    val number: String,
    val name: String,
    val durationMinutes: Int,
    val text: String,
    val imageLesson: String,
    val videoUrl: String?,
    val comments: List<String>,
    val deadline: Date
)