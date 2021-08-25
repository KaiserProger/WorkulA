package com.swaptech.workula.di.interactor

import com.swaptech.workula.domain.interactor.UserInteractor
import com.swaptech.workula.domain.interactor.impl.UserInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun bindUserInteractor(userInteractorImpl: UserInteractorImpl): UserInteractor
}