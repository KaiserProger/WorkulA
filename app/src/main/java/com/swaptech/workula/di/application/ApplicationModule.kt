package com.swaptech.workula.di.application

import com.swaptech.workula.di.activity.ActivityModule
import com.swaptech.workula.di.interactor.InteractorModule
import com.swaptech.workula.di.network.ApiModule
import com.swaptech.workula.di.network.InterceptorModule
import com.swaptech.workula.di.network.RetrofitModule
import com.swaptech.workula.di.repository.RepositoryModule
import com.swaptech.workula.di.viewModel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        RetrofitModule::class, ApiModule::class, RepositoryModule::class, InteractorModule::class,
        ActivityModule::class, ViewModelModule::class, InterceptorModule::class
    ]
)
class ApplicationModule