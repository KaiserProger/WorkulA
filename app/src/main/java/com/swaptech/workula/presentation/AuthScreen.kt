package com.swaptech.workula.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swaptech.workula.R
import com.swaptech.workula.WorkulaApp

@Composable
fun AuthScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
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
            modifier = Modifier.fillMaxSize(),
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
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
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

@Preview
@Composable
fun AuthPreview() {
    AuthScreen()
}
