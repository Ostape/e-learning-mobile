package com.robosh.basestartapplication.net.repository

import com.robosh.basestartapplication.net.api.ElearningApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val elearningApi: ElearningApi
) : AuthRepository {

    // todo should return Response class
    override fun loginUser(email: String, password: String) {
//        elearningApi.loginUser() should return Response
//        return LoginState.LoginSuccess
    }

    override fun getUser() {
        // should return Response User
    }
}