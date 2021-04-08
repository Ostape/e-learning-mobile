package com.robosh.basestartapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: String,
    val text: String,
    val nickname: String,
    val avatar: String = "https://cdn1.iconfinder.com/data/icons/avatar-97/32/avatar-02-512.png"
) : Parcelable