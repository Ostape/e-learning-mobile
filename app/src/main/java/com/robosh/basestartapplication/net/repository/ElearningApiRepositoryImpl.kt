package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.api.ElearningApi
import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.model.UserTokenResponse
import retrofit2.Response
import javax.inject.Inject

class ElearningApiRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : ElearningApiRepository {

    override suspend fun loginUser(userLoginRequest: UserLoginRequest): Response<UserTokenResponse> {
        return elearningApi.loginUser(userLoginRequest)
    }
}