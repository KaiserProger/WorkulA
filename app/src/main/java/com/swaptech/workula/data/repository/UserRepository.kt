package com.swaptech.workula.data.repository

import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignUpModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(signUpModel: SignUpModel): Flow<Session>
}