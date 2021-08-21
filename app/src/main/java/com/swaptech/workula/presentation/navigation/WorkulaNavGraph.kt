package com.swaptech.workula.presentation.navigation

enum class WorkulaNavGraph {
    Auth(),
    Chat();

    companion object {
        fun fromRoute(route: String?): WorkulaNavGraph =
            when (route?.substringBefore("/")) {
                Auth.name -> Auth
                Chat.name -> Chat
                null -> Auth
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}