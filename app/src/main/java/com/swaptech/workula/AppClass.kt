package com.swaptech.workula

import android.app.Application
import com.swaptech.workula.di.component.ApplicationComponent
import com.swaptech.workula.di.component.DaggerApplicationComponent

class AppClass: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}