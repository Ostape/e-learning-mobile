package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class UserTokenResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)