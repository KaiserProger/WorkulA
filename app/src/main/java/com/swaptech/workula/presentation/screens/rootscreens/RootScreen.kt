package com.swaptech.workula.presentation.screens.rootscreens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.swaptech.workula.DrawerItem
import com.swaptech.workula.HomeDestinations
import com.swaptech.workula.presentation.ext.replaceTo
import com.swaptech.workula.presentation.screens.rootscreens.notifications.NotificationsScreen
import com.swaptech.workula.presentation.screens.rootscreens.about.AboutScreen
import com.swaptech.workula.presentation.screens.rootscreens.addchat.AddChatScreen
import com.swaptech.workula.presentation.screens.rootscreens.chat.ChatScreen
import com.swaptech.workula.presentation.screens.rootscreens.createsuperchat.CreateSuperchatScreen
import com.swaptech.workula.presentation.screens.rootscreens.createtodo.CreateTodoScreen
import com.swaptech.workula.presentation.screens.rootscreens.edittodo.EditTodoScreen
import com.swaptech.workula.presentation.screens.rootscreens.home.HomeScreen
import com.swaptech.workula.presentation.screens.rootscreens.profile.ProfileScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val useDarkTheme = MaterialTheme.colors.isLight
    val statusBarColor = MaterialTheme.colors.primary
    Scaffold {
        val homeRoute = "${DrawerItem.Home.name}/{title}"
        val home = DrawerItem.Home.name
        //TODO
        NavHost(
            navController = navController,
            startDestination = homeRoute
        ) {
            composable(
                route = homeRoute,
                arguments = listOf(
                    navArgument("title") {
                        type = NavType.StringType
                        defaultValue = "Swap Tech"
                    }
                )
            ) { entry ->
                val title = entry.arguments?.getString("title").orEmpty()
                HomeScreen(
                    navController = navController,
                    title = title,
                    onDrawerItemClicked = { route ->
                        navController.replaceTo(route)
                    },
                    onChatItemClicked = {
                        navController.navigate(HomeDestinations.SelectedChat.name)
                    },
                    onBottomSheetListItemClicked = { selectedItem ->
                        navController.replaceTo(buildRouteWithArgs(home, selectedItem))
                    }
                )
            }

            composable(DrawerItem.Profile.name) {
                ProfileScreen(
                    onDrawerItemClicked = { route ->
                        //TODO
                        val navigationRoute = if(route == home) {
                            buildRouteWithArgs(home, "test")
                        } else route
                        navController.replaceTo(navigationRoute)
                    }
                )
            }

            composable(DrawerItem.About.name) {
                AboutScreen(
                    onDrawerItemClicked = { route ->
                        //TODO
                        val navigationRoute = if(route == home) {
                            buildRouteWithArgs(home, "hui")
                        } else route
                        navController.replaceTo(navigationRoute)
                    }
                )
            }

            composable(HomeDestinations.SelectedChat.name) {
                ChatScreen(
                    navController = navController
                )
            }

            composable(HomeDestinations.Notifications.name) {
                NotificationsScreen(
                    navController = navController
                )
            }

            composable(HomeDestinations.CreateSuperchat.name) {
                CreateSuperchatScreen(
                    navController = navController
                )
            }

            composable(HomeDestinations.AddChat.name) {
                AddChatScreen(
                    navController = navController
                )
            }

            composable(HomeDestinations.CreateTodo.name) {
                CreateTodoScreen(
                    navController = navController
                )
            }

            composable(HomeDestinations.EditTodo.name) {
                EditTodoScreen(
                    navController = navController
                )
            }
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkTheme
        )
        systemUiController.systemBarsDarkContentEnabled = false
    }
}

private fun buildRouteWithArgs(
    destination: String,
    vararg args: String
): String {
    var finalRoute = destination
    args.forEach { arg ->
        finalRoute += "/$arg"
    }
    return finalRoute
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview
@Composable
fun Preview() {
    RootScreen()
}