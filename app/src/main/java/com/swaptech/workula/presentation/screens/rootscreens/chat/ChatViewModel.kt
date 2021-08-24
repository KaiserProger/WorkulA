package com.swaptech.workula.presentation.screens.rootscreens.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.swaptech.workula.domain.models.Message
import com.swaptech.workula.domain.models.TodoModel

class ChatViewModel: ViewModel() {
    val messages by mutableStateOf(mutableListOf<Message>())
    val todos by mutableStateOf(
        MutableList(200) {
            TodoModel(
                title = listOf("[Android] Hui", "[Backend] Server", "[Test] Test", "[iOS] Swift",).random(),
                trackName = listOf("Workula-1", "Workula-5", "Workula-10", "Workula-12").random(),
                performers = mutableListOf()
            )
        }
    )

    fun onMessageSent(message: Message) {
        messages.add(message)
    }
}