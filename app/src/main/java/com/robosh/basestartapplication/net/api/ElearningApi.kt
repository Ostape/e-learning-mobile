package com.robosh.basestartapplication.net.api

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.net.model.CourseResponse
import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.model.UserResponse
import com.robosh.basestartapplication.net.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ElearningApi {

    private companion object {
        const val BASE_PREFIX = "e-learning-portal"
    }

    @POST("$BASE_PREFIX/auth/login")
    suspend fun loginUser(@Body userLoginRequest: UserLoginRequest): Response<UserTokenResponse>

    @GET("$BASE_PREFIX/users/user-data")
    suspend fun getUserData(@Header("Authorization") auth: String): Response<UserResponse>

    @GET("$BASE_PREFIX/courses/all")
    suspend fun getAllCourses(): Response<List<CourseResponse>>
}