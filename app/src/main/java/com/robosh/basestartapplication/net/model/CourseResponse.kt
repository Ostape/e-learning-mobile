package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photoPreview")
    val photoPreview: String?,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("lessons")
    val lessons: List<LessonResponse>
)