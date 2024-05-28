package com.demircandemir.relaysample.presentation.screens.chat

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.AIRepository
import com.demircandemir.relaysample.domain.model.gemini.MessageFromGemini
import com.demircandemir.relaysample.domain.model.open_ai.Message
import com.demircandemir.relaysample.domain.model.open_ai.MessageResponse
import com.demircandemir.relaysample.util.ApiResult
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


data class ChatGptState (
    var isLoading : Boolean = false,
    var error : String = "",
    var messageList : List<MessageResponse> = emptyList()
)

@HiltViewModel
class AIViewModel @Inject constructor(
    private val aiRepository: AIRepository,
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatGptState())
    val chatState: StateFlow<ChatGptState> = _chatState
    private val list = _chatState.value.messageList.toMutableList()
    private var messageList = list.map {
        it.message
    }.toMutableList()

    fun sendMessageToGpt(text: String) {

        val systemMessage = Message(
            content = "Sen bir yemek uzmanısın. Sadece yemek tarifleri, malzemeler, besin değerleri ve yemekle ilgili diğer konular hakkında cevap ver. Başka konularla ilgili sorulara yanıt verme.",
            role = "system"
        )

        val message = Message(content = text, role = "user")

        val messages = mutableListOf(systemMessage)

        messages.add(message)


        messageList.add(message)
        list.add(MessageResponse(message = message))


        Log.d("ChatGptViewModel", list.toString())
        _chatState.value = chatState.value.copy(messageList = list)


        viewModelScope.launch {
            aiRepository.sendMessageOpenAi(messages).collect{ result->
                when (result) {
                    is ApiResult.Loading -> {
                        _chatState.value = ChatGptState(isLoading = true, messageList = list)
                        Log.d("ChatGptViewModel", "Loading")
                    }
                    is ApiResult.Success -> {
                        val response = result.data?.choices
                        response?.let {
                            val botMessage = it[0].message.content
                            list.add(MessageResponse(message = Message(content = botMessage, role = "bot")))
//                            list.add(response?.get(0) ?: MessageResponse(message = Message(content = "Error", role = "bot")))
                            _chatState.value = ChatGptState(messageList = list, isLoading = false)
                            Log.d("ChatGptViewModel", "ResponseList:  $list")
                        } ?: run {
                            _chatState.value = ChatGptState(error = "Empty response", isLoading = false)
                            Log.d("ChatGptViewModel", "Empty response")
                        }
                    }
                    is ApiResult.Error -> {
                        Log.d("ChatGptViewModel", "Error - ${result.message.toString()}")
                        _chatState.value = result.message?.let { ChatGptState(error = it, isLoading = false) }!!
                    }
                }
            }
        }
    }


    private fun createGenerativeModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = "AIzaSyBD2qldNoHxUuzKxj3R7A1qP25-0QXUbgc",
        safetySettings = safetySettings
    )

    private val harassmentSafety = SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)
    private val sexualContentSafety = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.ONLY_HIGH)
    private val hateSpeechSafety = SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE)

    private val generativeModelVision by lazy { createGenerativeModel("gemini-pro-vision") }
    private val generativeModelText by lazy { createGenerativeModel("gemini-pro") }

    private val safetySettings by lazy {
        listOf(
            harassmentSafety,
            sexualContentSafety,
            hateSpeechSafety
        )
    }



    private var _messages = MutableStateFlow(listOf<MessageFromGemini>())
    val messagesState: StateFlow<List<MessageFromGemini>> = _messages


    fun sendMessageToGemini(message: String, image: Bitmap? = null) = viewModelScope.launch {
        _messages.value += MessageFromGemini(message, true, getTimestamp(), image)
    }

    fun receiveMessageFromGemini() = viewModelScope.launch {

        val newMessage = _messages.value.last().text

        val systemMessage = "Sen bir yemek uzmanısın. Sadece yemek tarifleri, malzemeler, besin değerleri ve yemekle ilgili diğer konular hakkında cevap ver. Başka konularla ilgili sorulara yanıt verme."
        val fullPrompt = "$systemMessage\n\nKullanıcının sorusu: $newMessage"

        val response = generativeModelText.generateContent(
            prompt = fullPrompt
        )
        if (response.text != null) {
            _messages.value += MessageFromGemini(response.text!!, false, getTimestamp())
        }

    }


    fun receiveImageResponseFromGemini(bitmap: Bitmap) = viewModelScope.launch {
        val inputContent = content {
            image(bitmap)
            text(_messages.value.last().text)
        }

        val response = generativeModelVision.generateContent(
            inputContent
        )
        if (response.text != null) {
            _messages.value += MessageFromGemini(response.text!!, false, getTimestamp())
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimestamp(): String {
        val sdf = SimpleDateFormat("HH:mm aa")
        return sdf.format(Date())
    }



    // Database Operations
//    fun insertMessage(message: ChatChefEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            chatChefRepository.insertMessage(message)
//        }
//    }
//
//    fun deleteMessage(message: ChatChefEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            chatChefRepository.deleteMessage(message)
//        }
//    }

}

