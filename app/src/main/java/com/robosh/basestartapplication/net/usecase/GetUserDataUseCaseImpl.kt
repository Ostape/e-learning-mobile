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
        val userBody = userDataResponse.body()
        return if (userDataResponse.isSuccessful && userBody != null) {
            AccountState.AccountData(userMapper.map(userBody))
        } else AccountState.Error
    }
}