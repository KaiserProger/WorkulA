package com.swaptech.workula.presentation.screens.rootscreens.createsuperchat

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.uicomponents.CreateEditChat

@Composable
fun CreateSuperchatScreen(
    navController: NavHostController
) {
    CreateEditChat(
        appBarTitle = stringResource(id = R.string.create_superchat),
        textFieldHint = stringResource(id = R.string.create_superchat),
        navController = navController
    )
}

@Preview
@Composable
fun CreateSuperchatScreen() {
    ThemedPreview {
        CreateSuperchatScreen(
            navController = rememberNavController()
        )
    }
}
