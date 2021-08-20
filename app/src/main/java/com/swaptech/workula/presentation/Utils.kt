package com.swaptech.workula.presentation

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.swaptech.workula.R

@Composable
fun WorkulaTextField(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    hint: String = ""
) {
    val (text, setText) = rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = setText,
        maxLines = maxLines,
        singleLine = singleLine,
        label = {
            Text(hint)
        }
    )
}

@Preview
@Composable
fun WorkulaTextFieldPreview() {
    WorkulaTextField()
}
