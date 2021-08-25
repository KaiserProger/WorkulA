package com.swaptech.workula.di.viewModel

import androidx.lifecycle.ViewModel
import com.swaptech.workula.presentation.screens.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelFactoryInjection(AuthViewModel::class)
    fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}