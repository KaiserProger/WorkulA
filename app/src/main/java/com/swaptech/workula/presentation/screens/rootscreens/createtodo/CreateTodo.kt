package com.swaptech.workula.presentation.screens.rootscreens.createtodo

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.swaptech.workula.R
import com.swaptech.workula.presentation.uicomponents.AddEditTodo

@Composable
fun CreateTodoScreen(
    navController: NavHostController
) {
    AddEditTodo(
        navController = navController,
        titleText = stringResource(id = R.string.create_todo),
        actionButtonText = stringResource(id = R.string.create_new_todo)
    )
}
