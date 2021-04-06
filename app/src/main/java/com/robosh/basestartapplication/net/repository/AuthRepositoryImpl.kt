package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.model.User
import com.robosh.basestartapplication.model.login.LoginState
import com.robosh.basestartapplication.net.api.ElearningApi
import com.robosh.basestartapplication.net.data.registeredUser
import com.robosh.basestartapplication.net.model.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : AuthRepository {

    // todo should return Response class
    override fun loginUser(email: String, password: String) {
//        elearningApi.loginUser() should return Response
//        return LoginState.LoginSuccess
    }
}