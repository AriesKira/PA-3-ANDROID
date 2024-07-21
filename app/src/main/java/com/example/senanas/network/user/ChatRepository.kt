package com.example.senanas.network.user

import com.example.senanas.model.MessageDto
import com.example.senanas.model.SendMessageDto
import retrofit2.Call

class ChatRepository(
    private val chatService: ChatService
) {
    fun getChat(token:String,chatId:Int): Call<List<MessageDto>> {
        return chatService.getChat(token,chatId)
    }

    fun sendMessage(token:String,sendMessageDto: SendMessageDto):Call<MessageDto> {
        return chatService.sendMessage(token,sendMessageDto)
    }
}