package com.swaptech.workula.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.swaptech.workula.DrawerItem
import com.swaptech.workula.di.viewModel.ViewModelFactory
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph
import com.swaptech.workula.presentation.screens.auth.AuthScreen
import com.swaptech.workula.presentation.screens.auth.AuthViewModel
import com.swaptech.workula.presentation.screens.rootscreens.RootScreen
import com.swaptech.workula.presentation.theme.WorkulaTheme

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun WorkulaApp(
    viewModelFactory: ViewModelFactory
) {
    WorkulaTheme {
        val allScreens = WorkulaNavGraph.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        //val currentScreen = WorkulaNavGraph.fromRoute(backStackEntry.value?.destination?.route)

        Scaffold {
            WorkulaNavHost(
                navController = navController,
                viewModelFactory = viewModelFactory
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
    navController: NavHostController,
    viewModelFactory: ViewModelFactory
) {
    //TODO: Replace condition with isAuthorized flag
    NavHost(
        navController = navController,
        startDestination = if (!false) WorkulaNavGraph.Auth.name else WorkulaNavGraph.Root.name
    ) {
        composable(WorkulaNavGraph.Auth.name) {
            AuthScreen(
                navController = navController,
                authViewModel = viewModel(
                    modelClass = AuthViewModel::class.java,
                    factory = viewModelFactory
                )
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

