package com.robosh.basestartapplication.model

data class Course(
    val id: Long,
    val name: String,
    val description: String,
    val courseImage: String?,
    val rating: Float?,
//    val teachers: List<Teacher>?,
    val lessons: List<Lesson>,
    val comments: List<Comment>? = null,
    var isCourseLiked: Boolean = false,
    var isStudying: Boolean = false
)