package com.robosh.basestartapplication.model.account

sealed class AccountEvent {

    object Loading : AccountEvent()
}