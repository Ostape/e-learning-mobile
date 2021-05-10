package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.model.CourseResponse
import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.model.UserResponse
import com.robosh.basestartapplication.net.model.UserTokenResponse
import retrofit2.Response

interface ElearningApiRepository {

    suspend fun loginUser(userLoginRequest: UserLoginRequest): Response<UserTokenResponse>

    suspend fun getUserData(): Response<UserResponse>

    suspend fun getAllCourses(): Response<List<CourseResponse>>
}