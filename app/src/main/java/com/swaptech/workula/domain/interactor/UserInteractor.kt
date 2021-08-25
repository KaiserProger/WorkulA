package com.swaptech.workula.domain.interactor

import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignUpModel
import kotlinx.coroutines.flow.Flow

interface UserInteractor {
    suspend fun signUp(signUpModel: SignUpModel): Flow<Session>
}