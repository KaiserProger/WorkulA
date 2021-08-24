package com.swaptech.workula.presentation.screens.rootscreens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.swaptech.workula.R
import com.swaptech.workula.presentation.uicomponents.ScaffoldWithNavDrawer

@Composable
fun AboutScreen(onDrawerItemClicked: (String) -> Unit) {
    ScaffoldWithNavDrawer(
        topAppBarTitle = stringResource(id = R.string.about),
        onDrawerItemClicked = onDrawerItemClicked
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "ABOUT")
        }
    }
}

@Preview
@Composable
fun Preview() {
    AboutScreen {

    }
}