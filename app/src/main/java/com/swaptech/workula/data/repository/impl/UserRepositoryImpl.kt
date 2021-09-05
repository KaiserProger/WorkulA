package com.swaptech.workula.data.repository.impl

import com.swaptech.workula.data.api.user.UserApi
import com.swaptech.workula.data.repository.UserRepository
import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignInModel
import com.swaptech.workula.domain.models.SignUpModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun signUp(signUpModel: SignUpModel): Flow<Session> = flow {
        emit(userApi.signUp(signUpModel))
    }.flowOn(Dispatchers.IO)

    override suspend fun signIn(signInModel: SignInModel): Flow<Session> = flow {
        emit(userApi.signIn(signInModel))
    }.flowOn(Dispatchers.IO)
}