package com.swaptech.workula.presentation.screens.rootscreens.addchat

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.uicomponents.CreateEditChat

@Composable
fun AddChatScreen(
    navController: NavHostController
) {
    CreateEditChat(
        appBarTitle = stringResource(id = R.string.add_chat),
        textFieldHint = stringResource(id = R.string.chat_name),
        navController = navController
    )
}

@Preview
@Composable
fun AddChatScreen() {
    ThemedPreview {
        AddChatScreen(
            navController = rememberNavController()
        )
    }
}