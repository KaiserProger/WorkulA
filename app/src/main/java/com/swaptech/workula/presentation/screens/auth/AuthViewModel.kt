package com.swaptech.workula.presentation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    var needRegistration by mutableStateOf(false)
    private set

    fun changeRegistrationStatus() {
        needRegistration = !needRegistration
    }

    fun needRegistration() {
        needRegistration = true
    }

    fun dontNeedRegistration() {
        needRegistration = false
    }
}