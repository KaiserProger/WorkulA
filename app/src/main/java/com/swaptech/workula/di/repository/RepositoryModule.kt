package com.swaptech.workula.di.repository

import com.swaptech.workula.data.repository.UserRepository
import com.swaptech.workula.data.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}