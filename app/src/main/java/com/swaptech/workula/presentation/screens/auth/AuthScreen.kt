package com.swaptech.workula.presentation.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.swaptech.workula.R
import com.swaptech.workula.presentation.theme.WorkulaTheme
import com.swaptech.workula.presentation.uicomponents.WorkulaButton
import com.swaptech.workula.presentation.uicomponents.WorkulaOutlinedButton
import com.swaptech.workula.presentation.uicomponents.WorkulaTextField

@Composable
fun AuthScreen(
    onTopButtonClick: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    Surface {
        Column {
            WelcomeHeader()
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (authViewModel.needRegistration) {
                        WorkulaTextField(
                            modifier = Modifier.padding(8.dp),
                            hint = stringResource(R.string.name)
                        )
                    }
                    WorkulaTextField(
                        modifier = Modifier.padding(8.dp),
                        hint = stringResource(R.string.email),
                        singleLine = true,
                        maxLines = 1
                    )
                    WorkulaTextField(
                        modifier = Modifier.padding(8.dp),
                        hint = stringResource(R.string.password),
                        singleLine = true,
                        maxLines = 1
                    )
                }
                Column {
                    WorkulaButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        text =
                        if (!authViewModel.needRegistration) stringResource(R.string.sign_up)
                        else stringResource(R.string.sign_in),
                        onClick = onTopButtonClick
                    )
                    WorkulaOutlinedButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        text =
                        if (!authViewModel.needRegistration) stringResource(R.string.sign_in)
                        else stringResource(R.string.sign_up),
                        onClick = { authViewModel.changeRegistrationStatus() }
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(R.string.welcome),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview
@Composable
fun AuthPreview() {
    WorkulaTheme {
        AuthScreen(onTopButtonClick = { })
    }
}
