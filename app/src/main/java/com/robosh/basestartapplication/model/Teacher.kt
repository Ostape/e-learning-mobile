package com.robosh.basestartapplication.model

data class Teacher(
    val id: String,
    val name: String,
    val surname: String,
    val university: University?,
    val rating: String?
)