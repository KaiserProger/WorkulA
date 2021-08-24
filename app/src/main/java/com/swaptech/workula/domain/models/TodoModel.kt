package com.swaptech.workula.domain.models

data class TodoModel(
    val title: String,
    val trackName: String,
    //TODO: Replace it
    val performers: MutableList<String>
)
