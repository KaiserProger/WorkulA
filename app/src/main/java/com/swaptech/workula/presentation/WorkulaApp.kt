package com.swaptech.workula.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.swaptech.workula.DrawerItem
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph
import com.swaptech.workula.presentation.screens.auth.AuthScreen
import com.swaptech.workula.presentation.screens.rootscreens.RootScreen
import com.swaptech.workula.presentation.theme.WorkulaTheme

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun WorkulaApp() {
    WorkulaTheme {
        val allScreens = WorkulaNavGraph.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        //val currentScreen = WorkulaNavGraph.fromRoute(backStackEntry.value?.destination?.route)

        Scaffold {
            WorkulaNavHost(
                navController = navController
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun WorkulaNavHost(
    navController: NavHostController
) {
    //TODO: Replace condition with isAuthorized flag
    NavHost(
        navController = navController,
        startDestination = if (!false) WorkulaNavGraph.Auth.name else WorkulaNavGraph.Root.name
    ) {
        composable(WorkulaNavGraph.Auth.name) {
            AuthScreen(
                onTopButtonClick = {
                    navController.navigate(DrawerItem.toString()) {
                        launchSingleTop = true
                    }
                }
            )
        }

        navigation(
            startDestination = WorkulaNavGraph.Root.name,
            route = DrawerItem.toString()
        ) {
            composable(WorkulaNavGraph.Root.name) {
                RootScreen()
            }
        }
    }
}

