package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)