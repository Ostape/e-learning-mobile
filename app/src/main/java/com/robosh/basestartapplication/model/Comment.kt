package com.robosh.basestartapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: Long,
    val text: String,
    val userId: Long
) : Parcelable