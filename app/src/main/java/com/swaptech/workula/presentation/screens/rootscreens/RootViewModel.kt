package com.swaptech.workula.presentation.screens.rootscreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RootViewModel: ViewModel() {
    //TODO: In future get value from db
    var selectedSuperchatName by mutableStateOf("Swap Tech")
    private set

    fun onSelectedSuperchatNameChanged(newValue: String) {
        selectedSuperchatName = newValue
    }
}