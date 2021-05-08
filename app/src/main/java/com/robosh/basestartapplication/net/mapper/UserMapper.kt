package com.robosh.basestartapplication.net.mapper

import com.robosh.basestartapplication.application.DEFAULT_USER_AVATAR
import com.robosh.basestartapplication.model.User
import com.robosh.basestartapplication.net.model.UserResponse
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(userResponse: UserResponse): User {
        return with(userResponse) {
            User(
                id,
                email,
                phone,
                name,
                surname,
                avatar ?: DEFAULT_USER_AVATAR
            )
        }
    }
}