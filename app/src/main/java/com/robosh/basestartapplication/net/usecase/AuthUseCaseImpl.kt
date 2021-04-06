package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.login.LoginState
import com.robosh.basestartapplication.net.repository.AuthRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override suspend fun execute(email: String, password: String): LoginState {
        authRepository.loginUser(email, password) // should get Return
        return LoginState.LoginSuccess
    }
}