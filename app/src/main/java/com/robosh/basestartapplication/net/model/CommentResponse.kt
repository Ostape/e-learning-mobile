package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id")
    val id: Long,
//    @SerializedName("date") // todo temporally commented as need custom serializer
//    val date: Date,
    @SerializedName("text")
    val text: String,
    @SerializedName("userId")
    val userId: Long
)