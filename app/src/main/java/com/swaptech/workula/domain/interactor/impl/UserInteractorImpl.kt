package com.swaptech.workula.domain.interactor.impl

import com.swaptech.workula.data.repository.UserRepository
import com.swaptech.workula.domain.interactor.UserInteractor
import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignUpModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository
): UserInteractor {
    override suspend fun signUp(signUpModel: SignUpModel): Flow<Session> =
        userRepository.signUp(signUpModel)
}