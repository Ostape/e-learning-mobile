package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.login.LoginState

interface AuthUseCase {

    suspend fun execute(email: String, password: String): LoginState
}