package com.robosh.basestartapplication.net.repository


interface AuthRepository {

    suspend fun loginUser(email: String, password: String)

    suspend fun getUser()
}