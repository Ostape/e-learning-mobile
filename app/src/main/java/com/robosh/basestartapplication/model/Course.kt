package com.robosh.basestartapplication.model

data class Course(
    val id: String,
    val name: String,
    val description: String,
    val courseImage: String,
    val rating: String?,
    val teachers: List<Teacher>?,
    val lessons: List<Lesson>,
    val comments: List<Comment>?,
    val deadline: String?
)