package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.model.UserTokenResponse
import retrofit2.Response

interface ElearningApiRepository {

    suspend fun loginUser(userLoginRequest: UserLoginRequest): Response<UserTokenResponse>
}