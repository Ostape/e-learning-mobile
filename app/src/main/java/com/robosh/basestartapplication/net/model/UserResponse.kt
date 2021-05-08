package com.robosh.basestartapplication.net.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String?
)