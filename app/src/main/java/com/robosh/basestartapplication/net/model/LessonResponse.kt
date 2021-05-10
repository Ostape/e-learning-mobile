package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("durationMinutes")
    val durationMinutes: String,
    @SerializedName("imageLesson")
    val imageLesson: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("videoUrl")
    val videoUrl: String?,
    @SerializedName("comments")
    val comments: List<CommentResponse>?
)