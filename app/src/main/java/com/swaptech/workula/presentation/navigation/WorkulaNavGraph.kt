package com.swaptech.workula.presentation.navigation

import androidx.annotation.DrawableRes
import com.swaptech.workula.R

enum class WorkulaNavGraph {
    Authorization,
    Root;

    enum class WorkulaDrawerScreensNavGraph(@DrawableRes val icon: Int) {
        Home(R.drawable.ic_baseline_home_24),
        Profile(R.drawable.ic_baseline_account_circle_24),
        About(R.drawable.ic_baseline_info_24);

        companion object {
        }

        enum class HomeDestinations {
            Notifications,
            AddChat,
            CreateSuperchat,
            SelectedChat,
            CreateTodo,
            EditTodo;
        }
    }

    companion object {
        fun fromRoute(route: String?): WorkulaNavGraph =
            when (route?.substringBefore("/")) {
                Authorization.name -> Authorization
                Root.name -> Root
                null -> Authorization
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}

