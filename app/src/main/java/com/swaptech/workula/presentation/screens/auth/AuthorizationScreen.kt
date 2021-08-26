package com.swaptech.workula.presentation.screens.auth

import android.content.Context
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
import com.swaptech.workula.presentation.navigation.WorkulaNavGraph
import com.swaptech.workula.presentation.theme.WorkulaTheme
import com.swaptech.workula.presentation.uicomponents.WorkulaButton
import com.swaptech.workula.presentation.uicomponents.WorkulaOutlinedButton
import com.swaptech.workula.presentation.uicomponents.WorkulaPasswordTextField
import com.swaptech.workula.presentation.uicomponents.WorkulaTextField
import com.swaptech.workula.presentation.utils.Validator
import com.swaptech.workula.showLongToast
import com.swaptech.workula.showShortToast

@Composable
fun AuthorizationScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
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
    val context = LocalContext.current
    AuthorizationScreen(
        viewModel = viewModel,
        name = name,
        context = context,
        email = email,
        password = password,
        setName = setName,
        setEmail = setEmail,
        setPassword = setPassword,
        navController = navController
    )
}

@Composable
private fun AuthorizationScreen(
    viewModel: AuthViewModel,
    name: String,
    context: Context,
    email: String,
    password: String,
    setName: (String) -> Unit,
    setEmail: (String) -> Unit,
    setPassword: (String) -> Unit,
    navController: NavHostController
) {
    Surface {
        Column {
            WelcomeHeader()
            if (viewModel.needRegistration) {
                RegistrationScreen(
                    onActionButtonClicked = {
                        when {
                            !Validator.isValidName(name) -> {
                                showLongToast(context, R.string.entered_name_is_not_valid)
                            }
                            !Validator.isValidEmail(email) -> {
                                showShortToast(context, R.string.entered_email_is_not_valid)
                            }
                            !Validator.isValidPassword(password) -> {
                                showLongToast(context, R.string.entered_password_is_not_valid)
                            }
                            else -> {
                                viewModel.signUp(
                                    SignUpModel(
                                        name = name,
                                        email = email,
                                        password = password
                                    )
                                )
                            }
                        }
                    },
                    onAdditionalActionButtonClicked = {
                        viewModel.changeRegistrationStatus()
                        setName("")
                    },
                    email = email,
                    setEmail = setEmail,
                    password = password,
                    setPassword = setPassword,
                    name = name,
                    setName = setName
                )
            } else {
                AuthenticationScreen(
                    onActionButtonClicked = {
                        when {
                            !Validator.isValidEmail(email) -> {
                                showShortToast(context, R.string.entered_email_is_not_valid)
                            }
                            !Validator.isValidPassword(password) -> {
                                showLongToast(context, R.string.entered_password_is_not_valid)
                            }
                            else -> {
                                //TODO: Add registration
                            }
                        }
                        navController.navigate(WorkulaNavGraph.Root.name)
                    },
                    onAdditionalActionButtonClicked = { viewModel.changeRegistrationStatus() },
                    email = email,
                    setEmail = setEmail,
                    password = password,
                    setPassword = setPassword
                )
            }
        }
    }
    if (viewModel.userSession != null) {
        Toast.makeText(
            LocalContext.current,
            "SUCCESS: ${viewModel.userSession}",
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun AuthenticationScreen(
    onActionButtonClicked: () -> Unit,
    onAdditionalActionButtonClicked: () -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit
) {
    AuthorizationScreenBase(
        onActionButtonClicked = onActionButtonClicked,
        onAdditionalActionButtonClicked = onAdditionalActionButtonClicked,
        actionButtonText = stringResource(id = R.string.sign_in),
        additionalActionButtonText = stringResource(id = R.string.sign_up),
        email = email,
        setEmail = setEmail,
        password = password,
        setPassword = setPassword
    )
}

@Composable
fun RegistrationScreen(
    onActionButtonClicked: () -> Unit,
    onAdditionalActionButtonClicked: () -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    name: String,
    setName: (String) -> Unit
) {
    AuthorizationScreenBase(
        content = {
            WorkulaTextField(
                modifier = Modifier.padding(8.dp),
                hint = stringResource(R.string.name),
                singleLine = true,
                maxLines = 1,
                text = name,
                setText = setName
            )
        },
        onActionButtonClicked = onActionButtonClicked,
        onAdditionalActionButtonClicked = onAdditionalActionButtonClicked,
        actionButtonText = stringResource(id = R.string.sign_up),
        additionalActionButtonText = stringResource(id = R.string.sign_in),
        email = email,
        setEmail = setEmail,
        password = password,
        setPassword = setPassword
    )
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

@Composable
fun AuthorizationScreenBase(
    content: @Composable () -> Unit = {},
    onActionButtonClicked: () -> Unit,
    onAdditionalActionButtonClicked: () -> Unit,
    actionButtonText: String,
    additionalActionButtonText: String,
    email: String,
    setEmail: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content()
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
                text = actionButtonText,
                onClick = onActionButtonClicked
            )
            WorkulaOutlinedButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = additionalActionButtonText,
                onClick = {
                    onAdditionalActionButtonClicked()
                    setPassword("")
                    setEmail("")
                }
            )
        }
    }
}

@Preview
@Composable
fun AuthPreview() {
    WorkulaTheme {
        AuthorizationScreen(
            rememberNavController(),
            viewModel = viewModel()
        )
    }
}
