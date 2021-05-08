package com.robosh.basestartapplication.model.login

sealed class LoginEvent {
    data class UserLoginClicked(val username: String, val password: String) : LoginEvent()
    object UpdateEvent : LoginEvent()
}