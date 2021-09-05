package com.swaptech.workula.presentation.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import kotlinx.coroutines.launch

@Composable
fun ScaffoldWithNavDrawer(
    topAppBarTitle: String,
    onDrawerItemClicked: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WorkulaTopAppBarWithNavigationIcon(
                titleText = topAppBarTitle,
                painter = painterResource(id = R.drawable.menu_icon),
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            WorkulaDrawer(onDrawerItemClicked = onDrawerItemClicked)
        },
        content = content
    )
}

@Composable
fun CreateEditChat(
    appBarTitle: String,
    textFieldHint: String,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WorkulaTopAppBar(
                titleText = appBarTitle,
                backButtonEnabled = true,
                backButtonClicked = {
                    navController.popBackStack()
                },
                actions = {
                    WorkulaIconButton(
                        painter = painterResource(id = R.drawable.accept_icon),
                        onClick = { navController.popBackStack() }
                    )
                }
            )
        }
    ) {
        LazyColumn {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfileIcon(
                        modifier = Modifier.padding(
                            top = 24.dp,
                            bottom = 24.dp
                        ),
                        onClick = { /*TODO*/ },
                        size = 160.dp
                    )
                }
                WorkulaTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    hint = textFieldHint,
                    maxLines = 1,
                    singleLine = true
                )
            }
        }
    }
}

@Composable
fun AddEditTodo(
    navController: NavHostController,
    titleText: String,
    actionButtonText: String
) {
    Scaffold(
        topBar = {
            WorkulaTopAppBar(
                titleText = titleText,
                backButtonEnabled = true,
                backButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(
                    start = 32.dp,
                    end = 32.dp,
                )
            ) {
                item {
                    WorkulaTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp
                            ),
                        hint = stringResource(id = R.string.todo_name)
                    )
                    WorkulaTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 32.dp
                            ),
                        hint = stringResource(id = R.string.todo_id)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = stringResource(id = R.string.performer_s),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            SelectedProfileIconWithAction(
                                size = 40.dp,
                                actionIconPainter = painterResource(id = R.drawable.remove_icon)
                            )
                        }
                        WorkulaIconButton(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = R.drawable.add_icon),
                            onClick = {},
                            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            WorkulaButton(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                text = actionButtonText,
                onClick = {}
            )
        }
    }
}

@Composable
fun SelectedProfileIconWithAction(
    modifier: Modifier = Modifier,
    size: Dp,
    actionIconPainter: Painter
) {
    Box(
        modifier = modifier
    ) {
        ProfileIcon(
            size = size
        )
        Surface(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary.copy(alpha = 0.3f))
                .alpha(0.3f)
                .size(size),
            content = {}
        )
        Icon(
            modifier = Modifier.size(size),
            painter = actionIconPainter,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun AddEditTodo() {
    ThemedPreview {
        AddEditTodo(
            navController = rememberNavController(),
            titleText = "edit",
            actionButtonText = "confirm"
        )
    }
}