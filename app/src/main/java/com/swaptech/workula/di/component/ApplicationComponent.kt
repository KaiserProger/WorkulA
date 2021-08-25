package com.swaptech.workula.di.component

import com.swaptech.workula.AppClass
import com.swaptech.workula.di.application.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class
    ]
)
@Singleton
interface ApplicationComponent: AndroidInjector<AppClass> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<AppClass>
}