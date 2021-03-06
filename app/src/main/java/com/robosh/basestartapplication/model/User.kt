package com.robosh.basestartapplication.model


data class User(
    val id: String,
    val email: String,
    val phoneNumber: String?,
    val name: String,
    val surname: String,
    val avatarUrl: String,
    val wishListCourses: MutableList<Course>? = mutableListOf(),
    val learningCourses: MutableList<Course>? = mutableListOf()
)