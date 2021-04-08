package com.robosh.basestartapplication.net.repository


interface AuthRepository {

    fun loginUser(email: String, password: String)

    fun getUser()
}