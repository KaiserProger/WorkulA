package com.swaptech.workula.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.swaptech.workula.presentation.screens.ChatScreen
import com.swaptech.workula.presentation.screens.AuthScreen

@ExperimentalPagerApi
@Composable
fun WorkulaNavHost(navController: NavHostController) {
    //TODO: Replace condition with isAuthorized flag
    NavHost(
        navController = navController,
        startDestination = if(false) WorkulaNavGraph.Auth.name else WorkulaNavGraph.Chat.name
    ) {
        composable(WorkulaNavGraph.Auth.name) {
            AuthScreen()
        }
        composable(WorkulaNavGraph.Chat.name) {
            ChatScreen()
        }
    }
}