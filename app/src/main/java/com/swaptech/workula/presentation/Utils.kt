package com.swaptech.workula.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.swaptech.workula.presentation.ui.theme.HintColor
import com.swaptech.workula.presentation.ui.theme.MessageBackground
import java.io.StringReader

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

@Composable
fun WorkulaTopAppBar(
    titleText: String,
    style: TextStyle = MaterialTheme.typography.body2,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Surface(
        color = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                        .size(32.dp)
                ) {
                    if (navigationIcon != null) {
                        navigationIcon()
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = titleText,
                    style = style,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            if (actions != null) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    content = actions
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(10.dp)
                            .size(32.dp)
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun ChatTextField(modifier: Modifier = Modifier, hint: String) {
    val (text, setText) = rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = setText,
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = hint,
                fontWeight = FontWeight.Bold,
                color = HintColor
            )
        }
    )
}

@Composable
fun WorkulaButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "Send")
    }
}

@Composable
fun Message(
    modifier: Modifier = Modifier,
    sender: String,
    text: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MessageBackground)
            .width(237.dp)
            .padding(8.dp),
        ) {
        Text(
            text = sender,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text
        )
    }
}

@Composable
fun SentMessage(
    modifier: Modifier = Modifier,
    sender: String,
    text: String
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Message(
            sender = sender,
            text = text
        )
    }
}

@Composable
fun ReceivedMessage(
    modifier: Modifier = Modifier,
    sender: String,
    text: String
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Message(
            sender = sender,
            text = text
        )
    }
}

@Preview
@Composable
fun JustPreview() {
    Scaffold {
        Column {
            SentMessage(
                sender = "Petr Petr",
                text = "HUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUI")
            ReceivedMessage(
                sender = "Petr Petr",
                text = "HUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUI")
        }
    }
}
