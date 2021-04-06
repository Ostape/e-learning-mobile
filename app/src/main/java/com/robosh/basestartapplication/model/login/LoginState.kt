package com.robosh.basestartapplication.model.login

sealed class LoginState {
    object LoginIdle : LoginState()
    object LoginSuccess : LoginState()
    data class LoginError(val name: String) : LoginState()
}