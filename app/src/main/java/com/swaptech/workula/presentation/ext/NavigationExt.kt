package com.swaptech.workula.presentation.ext

import androidx.navigation.NavHostController

fun NavHostController.replaceTo(destination: String) {
    this.navigate(destination) {
        this@replaceTo.popBackStack()
        launchSingleTop = true
    }
}

fun NavHostController.navigateSingle(destination: String) {
    this.navigate(destination) {
        launchSingleTop = true
    }
}