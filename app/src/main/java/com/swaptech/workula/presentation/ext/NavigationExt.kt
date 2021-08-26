package com.swaptech.workula.presentation.ext

import androidx.navigation.NavHostController

fun NavHostController.replaceTo(destination: String) {
    navigate(destination) {
        this@replaceTo.popBackStack()
        launchSingleTop = true
    }
}

fun NavHostController.safety(action: () -> Unit) {

}

fun NavHostController.navigateSingle(destination: String) {
    navigate(destination) {
        launchSingleTop = true
    }
}