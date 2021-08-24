package com.swaptech.workula.presentation.screens.rootscreens.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.swaptech.workula.HomeDestinations
import com.swaptech.workula.R
import com.swaptech.workula.domain.models.Message
import com.swaptech.workula.domain.models.TodoModel
import com.swaptech.workula.presentation.ext.lastIndex
import com.swaptech.workula.presentation.theme.WorkulaTheme
import com.swaptech.workula.presentation.uicomponents.ChatTextField
import com.swaptech.workula.presentation.uicomponents.ConfirmationPopup
import com.swaptech.workula.presentation.uicomponents.ProfileIcon
import com.swaptech.workula.presentation.uicomponents.ReceivedMessage
import com.swaptech.workula.presentation.uicomponents.SentMessage
import com.swaptech.workula.presentation.uicomponents.TODOCard
import com.swaptech.workula.presentation.uicomponents.WorkulaButton
import com.swaptech.workula.presentation.uicomponents.WorkulaTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun ChatScreen(
    navController: NavHostController,
    viewModel: ChatViewModel = viewModel(),
    messages: List<Message> = viewModel.messages,
    todos: List<TodoModel> = viewModel.todos,
    onMessageSent: (Message) -> Unit = viewModel::onMessageSent,
    pagerState: PagerState = rememberPagerState(pageCount = PAGE_COUNT),
    listState: LazyListState = rememberLazyListState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    textFieldState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    showPopupState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) {
    val showPopup = showPopupState.component1()
    val onShowPopStateChanged = showPopupState.component2()
    val text = textFieldState.component1()
    val onTextChanged = textFieldState.component2()
    Scaffold(
        topBar = {
            WorkulaTopAppBar(
                titleText = "Sample Group Chat",
                backButtonEnabled = true,
                backButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(
                modifier = Modifier
                    .height(48.dp)
                    .background(MaterialTheme.colors.primary),
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                listOf("Chat", "TODOs").forEachIndexed { index, title ->
                    Tab(
                        content = {
                            Text(text = title)
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    if (pagerState.lastIndex()) {
                                        pagerState.currentPage - 1
                                    } else {
                                        pagerState.currentPage + 1
                                    }
                                )
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                dragEnabled = false
            ) { page ->
                when (page) {
                    Tabs.Chat.position -> {
                        ChatTab(
                            listState = listState,
                            messages = messages,
                            text = text,
                            setText = onTextChanged,
                            onMessageSent = onMessageSent
                        )
                    }
                    Tabs.TODOs.position -> {
                        TODOSTab(
                            todos = todos,
                            onFABClick = {
                                navController.navigate(HomeDestinations.CreateTodo.name)
                                onShowPopStateChanged(false)
                            },
                            onTodoClick = { todoModel ->
                                onShowPopStateChanged(true)
                            }
                        )
                    }
                }
            }
            if (showPopup) {
                ConfirmationPopup(
                    onDismissRequest = { onShowPopStateChanged(false) },
                    onPositiveButtonClicked = {
                        navController.navigate(HomeDestinations.EditTodo.name)
                        onShowPopStateChanged(false)
                    },
                    onNegativeButtonClicked = { onShowPopStateChanged(false) }
                )
            }
        }
    }
}

@Composable
fun ChatTab(
    listState: LazyListState,
    messages: List<Message>,
    text: String,
    setText: (String) -> Unit,
    onMessageSent: (Message) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = listState
        ) {
            items(
                items = messages
            ) { message ->
                if (message.isReceived) {
                    ReceivedMessage(
                        sender = message.sender,
                        text = message.message
                    )
                } else {
                    SentMessage(
                        sender = message.sender,
                        text = message.message
                    )
                }
            }
        }
        Column {
            ChatTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                hint = stringResource(R.string.type_something),
                text = text,
                onTextChanged = setText
            )
            WorkulaButton(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.send),
                onClick = {
                    onMessageSent(
                        Message(
                            sender = listOf(
                                "Androider",
                                "Backender",
                                "Tester"
                            ).random(),
                            message = text,
                            isReceived = listOf(true, false).random()
                        )
                    )
                    setText("")
                }
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TODOSTab(
    todos: List<TodoModel>,
    onFABClick: () -> Unit,
    onTodoClick: (TodoModel) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(
                items = todos
            ) { todoModel ->
                TODOCard(
                    todoModel = todoModel,
                    performers = { ProfileIcon(size = 32.dp) },
                    onClick = onTodoClick,
                    todoId = "Workula-1"
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onFABClick,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = null
                )
            }
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview
@Composable
fun ChatScreenPreview() {
    WorkulaTheme {
        ChatScreen(
            navController = rememberNavController(),
        )
    }
}

private enum class Tabs(val position: Int) {
    Chat(0),
    TODOs(1)
}

private const val PAGE_COUNT = 2