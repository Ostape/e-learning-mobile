package com.robosh.basestartapplication.net.api

import com.robosh.basestartapplication.model.Course
import com.robosh.basestartapplication.model.User
import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ElearningApi {

    companion object {
        private const val API_KEY = "bf0af36b6b0149089510541e46af70f9"
        private const val API_KEY_QUERY = "?api_key=$API_KEY"
    }

    @POST("e-learning-portal/auth/login")
    suspend fun loginUser(@Body userLoginRequest: UserLoginRequest): Response<UserTokenResponse>

    @GET("/wishlist$API_KEY_QUERY")
    suspend fun getWishListReference(): Response<Course>

    @GET("/{user}$API_KEY_QUERY")
    suspend fun getUserById(): Response<User>

    @GET("/courses$API_KEY_QUERY")
    suspend fun getCoursesId(): Response<User>
}