package com.swaptech.workula.presentation.ext

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
fun PagerState.lastIndex() = currentPage == this.pageCount - 1