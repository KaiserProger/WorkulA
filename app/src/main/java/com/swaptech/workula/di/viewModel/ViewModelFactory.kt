package com.swaptech.workula.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelProvider = creators[modelClass]
        @Suppress("UNCHECKED_CAST")
        return viewModelProvider?.get() as T
            ?: throw IllegalArgumentException("Unknown view model class $modelClass")
    }
}