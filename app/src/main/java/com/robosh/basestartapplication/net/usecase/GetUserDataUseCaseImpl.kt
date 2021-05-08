package com.robosh.basestartapplication.net.usecase

import com.robosh.basestartapplication.model.account.AccountState
import com.robosh.basestartapplication.net.data.registeredUser
import javax.inject.Inject

class GetUserDataUseCaseImpl @Inject constructor(
//    private val authRepository: AuthRepository
) : GetUserDataUseCase {

    override suspend fun execute(): AccountState {
        return AccountState.AccountData(registeredUser.also {
//            it.wishListCourses?.add(coursesList.first())
        })
    }
}