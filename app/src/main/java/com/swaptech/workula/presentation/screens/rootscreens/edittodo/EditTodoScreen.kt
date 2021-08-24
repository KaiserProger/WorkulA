package com.swaptech.workula.presentation.screens.rootscreens.edittodo

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.uicomponents.AddEditTodo

@Composable
fun EditTodoScreen(
    navController: NavHostController
) {
    AddEditTodo(
        navController = navController,
        titleText = stringResource(id = R.string.edit_todo),
        actionButtonText = stringResource(id = R.string.confirm)
    )
}

@Preview
@Composable
fun EditTodoScreen() {
    ThemedPreview {
        EditTodoScreen(rememberNavController())
    }
}
