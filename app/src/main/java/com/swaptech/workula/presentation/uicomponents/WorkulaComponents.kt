package com.swaptech.workula.presentation.uicomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.domain.models.TodoModel
import com.swaptech.workula.presentation.theme.HintColor
import com.swaptech.workula.presentation.theme.LightGray
import com.swaptech.workula.presentation.theme.cardSurface

@Composable
fun WorkulaTextField(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    hint: String = "",
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
        },
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun WorkulaTextField(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    hint: String = "",
    text: String,
    setText: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = setText,
        maxLines = maxLines,
        singleLine = singleLine,
        label = {
            Text(hint)
        },
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun WorkulaPasswordTextField(
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    hint: String = "",
    text: String,
    setText: (String) -> Unit
) {
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = setText,
        maxLines = maxLines,
        singleLine = singleLine,
        label = { Text(hint) },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            WorkulaIconButton(
                onClick = { passwordVisibility.value = !passwordVisibility.value },
                painter = painterResource(
                    id = if (passwordVisibility.value) R.drawable.ic_baseline_visibility_off_24 else R.drawable.ic_baseline_visibility_24

                )
            )
        },
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun WorkulaTopAppBar(
    titleText: String = "",
    titleBody: @Composable (RowScope.() -> Unit)? = null,
    style: TextStyle = MaterialTheme.typography.body2,
    backButtonEnabled: Boolean = false,
    backButtonClicked: () -> Unit = {},
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    Surface(
        color = MaterialTheme.colors.primary
    ) {
        Row {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!backButtonEnabled) {
                        if (navigationIcon != null) {
                            navigationIcon()
                        }
                    } else {
                        IconButton(onClick = backButtonClicked) {
                            Icon(
                                painter = painterResource(R.drawable.back_icon),
                                contentDescription = null
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    if (titleBody != null) {
                        titleBody()
                    } else {
                        Row {
                            Text(
                                text = titleText,
                                style = style,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
                if (actions != null) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        content = actions
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkulaTopAppBarPreview() {
    WorkulaTopAppBar(
        titleBody = {
            Row(
                modifier = Modifier
                    .clickable {
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Swap Tech",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colors.onPrimary
                )
                Icon(
                    painter = painterResource(id = R.drawable.drop_down_list_arrow),
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            WorkulaIconButton(
                painter = painterResource(id = R.drawable.menu_icon),
                onClick = {
                }
            )
        },
        actions = {
            WorkulaIconButton(
                painter = painterResource(R.drawable.ic_baseline_notifications_none_24),
                onClick = { /*TODO*/ }
            )
            WorkulaIconButton(
                painter = painterResource(R.drawable.add_icon),
                onClick = { /*TODO*/ }
            )
        }
    )
}

@Preview
@Composable
fun WorkulaTopAppBarPreview2() {
    WorkulaTopAppBar(
        titleText = "Hui",
        backButtonEnabled = true,
        backButtonClicked = {
        }
    )
}

@Composable
fun WorkulaTopAppBarWithNavigationIcon(
    titleText: String,
    style: TextStyle = MaterialTheme.typography.body2,
    painter: Painter,
    onClick: () -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    WorkulaTopAppBar(
        titleText = titleText,
        style = style,
        navigationIcon = {
            WorkulaIconButton(
                painter = painter,
                onClick = onClick
            )
        },
        actions = actions
    )
}

@Composable
fun ChatTextField(
    modifier: Modifier = Modifier,
    hint: String,
    text: String,
    onTextChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences
        ),
        placeholder = {
            Text(
                text = hint,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.cardSurface
            )
        }
    )
}

@Composable
fun WorkulaButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun WorkulaOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = text)
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
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.cardSurface)
            .width(236.dp)
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

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Icon(
                painter = painter,
                contentDescription = null
            )
        }
        Spacer(
            modifier = Modifier.width(30.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Composable
fun WorkulaIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = tint
        )
    }
}

@Preview
@Composable
fun PreviewScreen() {
    Surface {
        ChatListItem(
            painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
            text = "Sample",
            onClick = {}
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun TODOCard(
    modifier: Modifier = Modifier,
    todoModel: TodoModel,
    onClick: (TodoModel) -> Unit = {},
    onLongClick: (TodoModel) -> Unit = {},
    performers: @Composable RowScope.() -> Unit,
    todoId: String,
) {
    Surface(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = { onClick(todoModel) },
                onLongClick = { onLongClick(todoModel) }
            )
            .fillMaxWidth(),
        color = MaterialTheme.colors.cardSurface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.TopStart),
                    text = todoModel.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = todoId,
                    fontSize = 12.sp
                )
            }
            Text(
                modifier = Modifier.padding(
                    top = 20.dp
                ),
                text = stringResource(id = R.string.performer_s),
                fontSize = 15.sp,
                color = if (MaterialTheme.colors.isLight) Color.Black.copy(alpha = 0.5f)
                else MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier.padding(
                    top = 8.dp
                ),
                content = performers
            )
        }
    }
}

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    size: Dp
) {
    Surface(
        modifier = modifier
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .size(size),
        color = Color.Gray,
        content = {}
    )
}

@Preview
@Composable
fun ProfileIcon() {
    ThemedPreview {
        ProfileIcon(onClick = { /*TODO*/ }, size = 32.dp)
    }
}