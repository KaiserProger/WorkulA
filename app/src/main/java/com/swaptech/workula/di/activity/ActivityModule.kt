package com.swaptech.workula.di.activity

import com.swaptech.workula.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributesMainActivity(): MainActivity
}