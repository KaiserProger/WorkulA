package com.swaptech.workula.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.swaptech.workula.WorkulaApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           //WorkulaApp()
            Scaffold(modifier = Modifier.fillMaxSize()) {
                AuthScreen()

            }
        }
    }
}