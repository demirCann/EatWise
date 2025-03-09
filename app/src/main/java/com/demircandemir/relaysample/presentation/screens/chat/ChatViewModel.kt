package com.demircandemir.relaysample.presentation.screens.chat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.ai.AIRepository
import com.demircandemir.relaysample.domain.model.AIModel
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.model.chat.*
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants.ERROR_GENERIC
import com.demircandemir.relaysample.util.Constants.SEND_MESSAGE_FAILED
import com.demircandemir.relaysample.util.Constants.USER_NAME_FAILED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: AIRepository,
    private val useCase: UseCases
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    init {
        getUserInfo()
    }

    fun handleMessageSend(
        currentModel: AIModel,
        messageText: String,
        selectedImage: Bitmap? = null
    ) {
        viewModelScope.launch {
            try {
                val content = when {
                    selectedImage != null && messageText.isNotBlank() -> {
                        MessageContent.TextWithImage(messageText, selectedImage)
                    }
                    selectedImage != null -> {
                        MessageContent.Image(selectedImage)
                    }
                    else -> {
                        MessageContent.Text(messageText)
                    }
                }

                val userMessage = ChatMessage(
                    content = content,
                    sender = MessageSender.User(
                        id = _chatState.value.userInfo.user_id ?: "",
                        name = _chatState.value.userInfo.name ?: ""
                    )
                )

                // Optimistic update - add user message immediately
                updateMessages(currentModel) { messages -> messages + userMessage }

                // Send message to AI
                repository.sendMessage(userMessage, currentModel).collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _chatState.update { it.copy(status = ChatStatus.TYPING) }
                        }
                        is ApiResult.Success -> {
                            result.data?.let { msg ->
                                updateMessages(currentModel) { messages -> messages + listOf(msg) }
                            }
                            _chatState.update { it.copy(status = ChatStatus.IDLE) }
                        }
                        is ApiResult.Error -> {
                            val errorMessage = ChatMessage(
                                content = MessageContent.Text(result.message ?: ERROR_GENERIC),
                                sender = when (currentModel) {
                                    AIModel.GEMINI -> MessageSender.AI.Gemini
                                    AIModel.GPT -> MessageSender.AI.GPT
                                },
                                status = MessageStatus.ERROR
                            )
                            updateMessages(currentModel) { messages -> messages + errorMessage }
                            _chatState.update { 
                                it.copy(
                                    status = ChatStatus.IDLE,
                                    error = result.message?.let { it1 -> Error.AI(it1) }
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _chatState.update { 
                    it.copy(error = Error.Generic(e.message ?: SEND_MESSAGE_FAILED))
                }
            }
        }
    }

    fun switchModel(newModel: AIModel) {
        _chatState.update { it.copy(currentModel = newModel) }
    }

    private fun updateMessages(model: AIModel, update: (List<ChatMessage>) -> List<ChatMessage>) {
        _chatState.update { state ->
            val currentMessages = state.messages[model] ?: emptyList()
            val updatedMessages = update(currentMessages)
            state.copy(
                messages = state.messages + (model to updatedMessages)
            )
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            try {
                useCase.getUserInfoFromRemoteUseCase().collect { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            _chatState.update {
                                it.copy(userInfo = result.data ?: UserInfo())
                            }
                        }
                        is ApiResult.Error -> {
                            _chatState.update {
                                it.copy(error = Error.Network(result.message ?: USER_NAME_FAILED))
                            }
                        }
                        is ApiResult.Loading -> {
                            _chatState.update { it.copy(error = null) }
                        }
                    }
                }
            } catch (e: Exception) {
                _chatState.update { 
                    it.copy(error = Error.Generic(e.message ?: USER_NAME_FAILED))
                }
            }
        }
    }
}