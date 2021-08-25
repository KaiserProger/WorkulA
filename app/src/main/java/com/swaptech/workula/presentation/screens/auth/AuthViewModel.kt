package com.swaptech.workula.presentation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaptech.workula.domain.interactor.UserInteractor
import com.swaptech.workula.domain.models.Session
import com.swaptech.workula.domain.models.SignUpModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val userInteractor: UserInteractor
): ViewModel() {
    var needRegistration by mutableStateOf(false)
    private set

    var userSession: Session? by mutableStateOf(null)
    private set
    fun changeRegistrationStatus() {
        needRegistration = !needRegistration
    }

    fun signUp(signUpModel: SignUpModel) {
        viewModelScope.launch {
            userInteractor.signUp(signUpModel).collect { session ->
                userSession = session
            }
        }
    }
}