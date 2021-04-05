package com.robosh.basestartapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val posterUrl: String,
    val date: String
) : Parcelable