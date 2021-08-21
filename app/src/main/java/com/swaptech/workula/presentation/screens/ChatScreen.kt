package com.swaptech.workula.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.swaptech.workula.R
import com.swaptech.workula.presentation.ChatTextField
import com.swaptech.workula.presentation.WorkulaButton
import com.swaptech.workula.presentation.WorkulaTopAppBar
import com.swaptech.workula.presentation.ui.theme.WorkulaTheme

@ExperimentalPagerApi
@Composable
fun ChatScreen() {
    val pagerState = rememberPagerState(pageCount = 2)
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = {
            WorkulaTopAppBar(
                titleText = "Sample Group Chat",
                navigationIcon = {
                    IconButton(onClick = { Log.d("OKOK", "HUI") }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(
                modifier = Modifier.height(48.dp),
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
                        onClick = { /*TODO*/ }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true,
                state = scrollState
            ) {

            }
            Column {
                ChatTextField(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    hint = stringResource(R.string.type_something)
                )
                WorkulaButton(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun ChatScreenPreview() {
    WorkulaTheme {
        ChatScreen()
    }
}