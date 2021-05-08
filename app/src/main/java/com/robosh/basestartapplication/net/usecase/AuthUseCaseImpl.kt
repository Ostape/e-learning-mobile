package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.login.LoginState
import com.robosh.basestartapplication.net.RetrofitClientInstance
import com.robosh.basestartapplication.net.model.UserLoginRequest
import com.robosh.basestartapplication.net.repository.ElearningApiRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val elearningApiRepository: ElearningApiRepository
) : AuthUseCase {

    override suspend fun execute(username: String, password: String): LoginState {
        val loginResponse = elearningApiRepository.loginUser(UserLoginRequest(username, password))
        return if (loginResponse.isSuccessful) {
            RetrofitClientInstance.YOUR_TOKEN = loginResponse.body()?.token ?: ""
            LoginState.LoginSuccess
        } else {
            LoginState.LoginError(loginResponse.errorBody().toString())
        }
    }
}