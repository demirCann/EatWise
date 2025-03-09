package com.demircandemir.relaysample.presentation.screens.chat

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.AIModel
import com.demircandemir.relaysample.domain.model.chat.ChatStatus
import com.demircandemir.relaysample.domain.model.chat.MessageContent
import com.demircandemir.relaysample.domain.model.chat.MessageSender
import com.demircandemir.relaysample.domain.model.chat.MessageStatus
import com.demircandemir.relaysample.util.defaultSuggestions
import com.demircandemir.relaysample.util.imageRelatedSuggestions

@Composable
fun ChatScreen(
    navController: NavHostController,
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val uiState by chatViewModel.chatState.collectAsStateWithLifecycle()
    val userName = uiState.userInfo.name
    var messageText by rememberSaveable { mutableStateOf("") }
    var isImageSelected by remember { mutableStateOf(false) }
    var selectedImage by remember { mutableStateOf<Bitmap?>(null) }
    val lazyListState = rememberLazyListState()

    val randomSuggestions = remember(isImageSelected) {
        if (isImageSelected) {
            imageRelatedSuggestions.shuffled().take(3)
        } else {
            defaultSuggestions.shuffled().take(5)
        }
    }

    LaunchedEffect(isImageSelected) {
        lazyListState.scrollToItem(0)
    }

    Scaffold(
        topBar = {
            ChatScreenTopBar(
                currentModel = uiState.currentModel,
                onModelSelected = { selectedModel ->
                    chatViewModel.switchModel(selectedModel)
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
                .navigationBarsPadding()
        ) {
            if (((uiState.currentModel == AIModel.GEMINI) && uiState.messages[AIModel.GEMINI].orEmpty().isEmpty()) ||
                ((uiState.currentModel == AIModel.GPT) && uiState.messages[AIModel.GPT].orEmpty().isEmpty())) {
                WelcomeSection(
                    userName = userName ?: "",
                    suggestions = randomSuggestions,
                    suggestionsLazyListState = lazyListState,
                    modifier = Modifier.weight(1f),
                    onSuggestionClick = { suggestion ->
                        messageText = suggestion
                    }
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    reverseLayout = true
                ) {
                    when (uiState.currentModel) {
                        AIModel.GEMINI, AIModel.GPT -> {
                            if (uiState.status == ChatStatus.TYPING) {
                                item {
                                    TypingAnimation(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    )
                                }
                            }
                            
                            items(uiState.messages[uiState.currentModel].orEmpty().reversed()) { message ->
                                AnimatedVisibility(
                                    visible = true,
                                    enter = fadeIn() +
                                            slideInVertically(
                                                initialOffsetY = { it / 2 }
                                            ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    ChatMessage(
                                        text = when(val content = message.content) {
                                            is MessageContent.Text -> content.text
                                            is MessageContent.Image -> content.caption ?: ""
                                            is MessageContent.TextWithImage -> content.text
                                        },
                                        image = when(val content = message.content) {
                                            is MessageContent.Image -> content.image
                                            is MessageContent.TextWithImage -> content.image
                                            else -> null
                                        },
                                        horizontalAlignment = when(message.sender) {
                                            is MessageSender.User -> Alignment.End
                                            is MessageSender.AI -> Alignment.Start
                                        },
                                        time = message.timestamp,
                                        isError = message.status == MessageStatus.ERROR
                                    )
                                }
                            }
                        }
                    }
                }
            }

            ModernTextFieldContainer(
                value = messageText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 12.dp, end = 16.dp),
                onValueChange = { messageText = it },
                onSendClick = {
                    if (messageText.isNotEmpty()) {
                        chatViewModel.handleMessageSend(
                            currentModel = uiState.currentModel,
                            messageText = messageText,
                            selectedImage = selectedImage
                        )
                        messageText = ""
                        isImageSelected = false
                        selectedImage = null
                    }
                },
                onImageCaptured = { selected, bitmap ->
                    isImageSelected = selected
                    selectedImage = bitmap
                }
            )
        }
    }
}

@Composable
private fun WelcomeSection(
    userName: String,
    suggestions: List<String>,
    suggestionsLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    onSuggestionClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 180.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        ) {
            Text(
                text = stringResource(R.string.chat_screen_greeting, userName),
                style = TextStyle(
                    fontSize = 35.sp,
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Green, Color.DarkGray)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.chat_screen_helping_question),
                style = TextStyle(
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                textAlign = TextAlign.Start
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            verticalArrangement = Arrangement.Bottom
        ) {
            SuggestionsRow(
                suggestions = suggestions,
                suggestionsLazyListState = suggestionsLazyListState,
                modifier = Modifier.padding(12.dp),
                onSuggestionClick = onSuggestionClick
            )
        }
    }
}

@Composable
fun SuggestionsRow(
     suggestions: List<String>,
     suggestionsLazyListState: LazyListState,
     modifier: Modifier = Modifier,
     onSuggestionClick: (String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        state = suggestionsLazyListState
    ) {
        items(suggestions) { suggestion ->
            CustomCard(
                suggestion = suggestion,
                onClick = { onSuggestionClick(suggestion) }
            )
        }
    }
}