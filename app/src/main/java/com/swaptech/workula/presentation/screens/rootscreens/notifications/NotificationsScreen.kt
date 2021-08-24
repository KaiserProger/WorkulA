package com.swaptech.workula.presentation.screens.rootscreens.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.uicomponents.WorkulaTopAppBar

@Composable
fun NotificationsScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WorkulaTopAppBar(
                titleText = stringResource(id = R.string.notifications),
                backButtonEnabled = true,
                backButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.nothing_to_show),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview
@Composable
fun NotificationsScreen() {
    ThemedPreview {
        NotificationsScreen(rememberNavController())
    }
}
