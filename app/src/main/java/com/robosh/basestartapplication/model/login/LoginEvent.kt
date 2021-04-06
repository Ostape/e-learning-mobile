package com.robosh.basestartapplication.model.login

sealed class LoginEvent {
    data class UserLoginClicked(val email: String, val password: String) : LoginEvent()
    object UpdateEvent : LoginEvent()
}