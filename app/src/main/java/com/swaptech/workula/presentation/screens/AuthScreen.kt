package com.swaptech.workula.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swaptech.workula.R
import com.swaptech.workula.presentation.WorkulaTextField
import com.swaptech.workula.presentation.ui.theme.WorkulaTheme

@Composable
fun AuthScreen() {
    Surface {
        Column {
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WorkulaTextField(
                    modifier = Modifier
                        .padding(8.dp),
                    hint = stringResource(R.string.email)
                )
                WorkulaTextField(
                    modifier = Modifier
                        .padding(8.dp),
                    hint = stringResource(R.string.password)
                )
            }
            Column {
                Button(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    //TODO: Add progress indicator to here
                    Text(text = stringResource(R.string.sign_in))
                }
                OutlinedButton(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = stringResource(R.string.sign_up))
                }
            }
        }
    }
}

@Preview
@Composable
fun AuthPreview() {
    WorkulaTheme() {
        AuthScreen()
    }
}
