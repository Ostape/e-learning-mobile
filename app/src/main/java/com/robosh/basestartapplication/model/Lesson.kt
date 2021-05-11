package com.robosh.basestartapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Lesson(
    val id: Long,
//    val number: String,
    val name: String,
    val durationMinutes: Int,
    val text: String,
    val imageLesson: String,
    val videoUrl: String? = "",
    val comments: List<Comment>?
//    ,
//    val deadline: Date
) : Parcelable