package com.demircandemir.relaysample.presentation.screens.chat


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demircandemir.relaysample.util.suggestions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChatScreen(
    aiViewModel: AIViewModel = hiltViewModel()
) {


    val chatState by aiViewModel.chatState.collectAsState()
    val name = remember {
        mutableStateOf("")
    }
    val randomSuggestions = remember {
        suggestions.shuffled().take(5)
    }

    val messagesFromGemini by aiViewModel.messagesState.collectAsState()


    LaunchedEffect(true) {
        val userData = FirebaseAuth.getInstance().currentUser?.displayName?.split(" ")?.get(0)
        name.value = userData.toString()
        val userImage = FirebaseAuth.getInstance().currentUser?.photoUrl?.userInfo
    }

    var aiModel by remember {
        mutableStateOf("Gemini")
    }

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ChatScreenTopBar(
                aiModel = aiModel,
                onModelSelected = { selectedModel ->
                    aiModel = selectedModel
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .imePadding()
                .navigationBarsPadding()
        ) {


            if (chatState.error.isNotBlank()) {
                Text(text = chatState.error)
            }


            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                reverseLayout = true
            ) {

                if (aiModel == "Gemini") {
                    items(messagesFromGemini.reversed()) { messageItem ->
                        if (messageItem.isUser) {
                            UserChatMessage(
                                text = messageItem.text,
                                horizontalAlignment = Alignment.End
                            )
                        } else {
                            AIChatMessage(
                                messageContent = messageItem.text
                            )
                        }
                    }
                } else if (aiModel == "Chat-GPT") {
                    items(chatState.messageList.reversed()) { messageItem ->
                        if (messageItem.message.isUser) {
                            UserChatMessage(
                                text = messageItem.message.content,
                                horizontalAlignment = Alignment.End
                            )
                        } else {
                            AIChatMessage(
                                messageContent = messageItem.message.content
                            )
                        }
                    }
                }
            }

            if (((aiModel == "Gemini") && messagesFromGemini.isEmpty()) || ((aiModel == "Chat-GPT") && chatState.messageList.isEmpty())) {
                Text(
                    text = "Selam ${name.value},",
                    style = TextStyle(
                        fontSize = 35.sp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Green, Color.DarkGray)
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Size nasıl yardımcı olabilirim?",
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.weight(1f))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    items(randomSuggestions) { suggestion ->
                        CustomCard(
                            suggestion = suggestion,
                            onClick = {
                                if (aiModel == "Gemini") {
                                    aiViewModel.sendMessageToGemini(suggestion)
                                    aiViewModel.receiveMessageFromGemini()
                                } else if (aiModel == "Chat-GPT") {
                                    aiViewModel.sendMessageToGpt(suggestion)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            textColor = Color.DarkGray
                        )
                    }
                }
            }

            if (chatState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(12.dp))
            }

            BottomContainer() { message ->

                if (aiModel == "Gemini") {
                    aiViewModel.sendMessageToGemini(message)
                    aiViewModel.receiveMessageFromGemini()
                } else if (aiModel == "Chat-GPT") {
                    aiViewModel.sendMessageToGpt(message)
                }

            }

        }

    }
}