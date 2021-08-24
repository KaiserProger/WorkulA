package com.swaptech.workula.presentation.screens.rootscreens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.swaptech.workula.HomeDestinations
import com.swaptech.workula.R
import com.swaptech.workula.ThemedPreview
import com.swaptech.workula.presentation.uicomponents.ChatListItem
import com.swaptech.workula.presentation.uicomponents.WorkulaDrawer
import com.swaptech.workula.presentation.uicomponents.WorkulaIconButton
import com.swaptech.workula.presentation.uicomponents.WorkulaTopAppBar
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    title: String,
    navController: NavHostController,
    onDrawerItemClicked: (String) -> Unit,
    onChatItemClicked: () -> Unit,
    onBottomSheetListItemClicked: (String) -> Unit
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val bottomSheetCornerRounding by animateDpAsState(
        if (
            bottomSheetState.currentValue == ModalBottomSheetValue.Expanded
        ) {
            0.dp
        } else 20.dp
    )

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            HomeBottomSheet(
                onCloseButtonClicked = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                },
                onActionButtonClicked = { navController.navigate(HomeDestinations.CreateSuperchat.name) },
                onListItemClicked = onBottomSheetListItemClicked
            )
        },
        sheetShape = RoundedCornerShape(
            topStart = bottomSheetCornerRounding,
            topEnd = bottomSheetCornerRounding
        ),
        sheetElevation = 2.dp,
        sheetState = bottomSheetState,
        content = {
            Scaffold(
                topBar = {
                    WorkulaTopAppBar(
                        titleBody = {
                            ClickableTitle(
                                onClick = {
                                    scope.launch {
                                        bottomSheetState.show()
                                    }
                                },
                                painter = painterResource(id = R.drawable.drop_down_list_arrow),
                                title = title
                            )
                        },
                        navigationIcon = {
                            WorkulaIconButton(
                                painter = painterResource(id = R.drawable.menu_icon),
                                onClick = {
                                    scope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                }
                            )
                        },
                        actions = {
                            WorkulaIconButton(
                                painter = painterResource(R.drawable.ic_baseline_notifications_none_24),
                                onClick = { navController.navigate(HomeDestinations.Notifications.name) }
                            )
                            //Show this button if only user is admin of superchat
                            WorkulaIconButton(
                                painter = painterResource(R.drawable.add_icon),
                                onClick = { navController.navigate(HomeDestinations.AddChat.name) }
                            )
                        }
                    )
                },
                drawerContent = {
                    WorkulaDrawer(
                        onDrawerItemClicked = {
                            onDrawerItemClicked(it)
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    )
                },
                scaffoldState = scaffoldState,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Column {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        state = listState
                    ) {
                        items(items = List(200) { "Sample Chat Number $it" }) { name ->
                            ChatListItem(
                                text = name,
                                painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                                onClick = onChatItemClicked
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun Hook(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .width(30.dp)
            .height(4.dp)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
    )
}

@Composable
fun ClickableTitle(
    onClick: () -> Unit,
    title: String,
    painter: Painter? = null
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colors.onPrimary
        )
        painter?.let {
            Icon(
                painter = painter,
                contentDescription = null
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeBottomSheet(
    onCloseButtonClicked: () -> Unit,
    onActionButtonClicked: () -> Unit,
    onListItemClicked: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(
                    top = 3.dp,
                    bottom = 3.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            content = { Hook() }
        )
        Row(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "Select superchat"
            )
            WorkulaIconButton(
                painter = painterResource(id = R.drawable.close_button),
                onClick = onCloseButtonClicked
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = List(200) { "$it" }
                ) { title ->
                    ChatListItem(
                        text = title,
                        painter = painterResource(id = R.drawable.menu_icon),
                        onClick = {
                            onListItemClicked(title)
                        }
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .height(48.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        content = { /*This is just a stub*/ }
                    )
                }
            }
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                onClick = onActionButtonClicked,
                shape = RoundedCornerShape(20.dp),
                content = { Text(text = stringResource(id = R.string.create_new_superchat)) }
            )
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun HomeScreen() {
    ThemedPreview {
        HomeScreen(
            title = "Swap Tech",
            navController = rememberNavController(),
            onChatItemClicked = {},
            onDrawerItemClicked = {},
            onBottomSheetListItemClicked = {}
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun HomeBottomSheet() {
    ThemedPreview {
        HomeBottomSheet(
            onCloseButtonClicked = {},
            onActionButtonClicked = {},
            onListItemClicked = {}
        )
    }
}
