package com.swaptech.workula.data.api.user

import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignInModel
import com.swaptech.workula.domain.models.SignUpModel
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/auth/signup")
    suspend fun signUp(@Body signUpModel: SignUpModel): Session

    @POST("auth/signin")
    suspend fun signIn(@Body signInModel: SignInModel): Session
}