package com.robosh.basestartapplication.model.account

import com.robosh.basestartapplication.model.User

sealed class AccountState {

    data class AccountData(val user: User) : AccountState()
    object Idle : AccountState()
    object Error : AccountState()
}