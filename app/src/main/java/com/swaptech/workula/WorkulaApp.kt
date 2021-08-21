package com.swaptech.workula

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph
import com.swaptech.workula.presentation.ui.theme.WorkulaTheme

@Composable
fun WorkulaApp() {
    WorkulaTheme {
        val allScreens = WorkulaNavGraph.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = WorkulaNavGraph.fromRoute(backStackEntry.value?.destination?.route)
        Scaffold {

        }
    }
}
