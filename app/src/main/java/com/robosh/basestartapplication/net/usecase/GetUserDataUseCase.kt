package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.account.AccountState

interface GetUserDataUseCase {

    suspend fun execute(): AccountState
}