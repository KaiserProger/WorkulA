package com.swaptech.workula.domain.models

data class Message(
    val sender: String,
    val message: String,
    val isReceived: Boolean
)
