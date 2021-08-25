package com.swaptech.workula.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.domain.models.SignUpModel
import com.swaptech.workula.presentation.theme.WorkulaTheme
import com.swaptech.workula.presentation.uicomponents.WorkulaButton
import com.swaptech.workula.presentation.uicomponents.WorkulaOutlinedButton
import com.swaptech.workula.presentation.uicomponents.WorkulaPasswordTextField
import com.swaptech.workula.presentation.uicomponents.WorkulaTextField

@Composable
fun AuthScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val (name, setName) = rememberSaveable {
        mutableStateOf("")
    }
    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
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
                            hint = stringResource(R.string.name),
                            text = name,
                            setText = setName
                        )
                    }
                    WorkulaTextField(
                        modifier = Modifier.padding(8.dp),
                        hint = stringResource(R.string.email),
                        singleLine = true,
                        maxLines = 1,
                        text = email,
                        setText = setEmail
                    )
                    WorkulaPasswordTextField(
                        modifier = Modifier.padding(8.dp),
                        hint = stringResource(R.string.password),
                        singleLine = true,
                        maxLines = 1,
                        text = password,
                        setText = setPassword
                    )
                }
                Column {
                    WorkulaButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        text =
                        if (!authViewModel.needRegistration) stringResource(R.string.sign_in)
                        else stringResource(R.string.sign_up),
                        onClick = {
                            authViewModel.signUp(
                                SignUpModel(
                                    name = name,
                                    email = email,
                                    password = password
                                )
                            )
                        }
                    )
                    WorkulaOutlinedButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        text =
                        if (!authViewModel.needRegistration) stringResource(R.string.sign_up)
                        else stringResource(R.string.sign_in),
                        onClick = {
                            authViewModel.changeRegistrationStatus()
                            setEmail("")
                            setName("")
                            setPassword("")
                        }
                    )
                }
            }
        }
    }
    if (authViewModel.userSession != null) {
        Toast.makeText(
            LocalContext.current,
            "SUCCESS: ${authViewModel.userSession}",
            Toast.LENGTH_LONG
        ).show()
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
        AuthScreen(
            rememberNavController(),
            viewModel()
        )
    }
}
