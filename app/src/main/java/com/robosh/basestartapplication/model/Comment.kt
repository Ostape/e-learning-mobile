package com.robosh.basestartapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: String,
    val text: String,
    val nickname: String
) : Parcelable