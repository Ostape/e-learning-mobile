package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable


data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val posterPath: String
)
