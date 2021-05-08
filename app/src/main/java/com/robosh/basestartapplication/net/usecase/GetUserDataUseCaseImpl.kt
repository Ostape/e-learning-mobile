package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.account.AccountState
import com.robosh.basestartapplication.net.mapper.UserMapper
import com.robosh.basestartapplication.net.repository.ElearningApiRepository
import javax.inject.Inject

class GetUserDataUseCaseImpl @Inject constructor(
    private val elearningApiRepository: ElearningApiRepository,
    private val userMapper: UserMapper
) : GetUserDataUseCase {

    override suspend fun execute(): AccountState {
        val userDataResponse = elearningApiRepository.getUserData()
        if (userDataResponse.isSuccessful) {
//            AccountState.AccountData()
        }

        return AccountState.Error
    }
}