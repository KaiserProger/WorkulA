package com.swaptech.workula.domain.models

data class Session(
    val userId: Int,
    val sessionId: String,
    val createdAt: Int
)
