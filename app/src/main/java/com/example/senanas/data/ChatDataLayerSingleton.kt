package com.example.senanas.data

import com.example.senanas.network.user.ChatRepository
import com.example.senanas.network.user.ChatService
import com.example.senanas.network.user.TicketService
import com.example.senanas.viewmodels.ChatViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatDataLayerSingleton {
    private lateinit var retrofitClient: Retrofit
    private lateinit var chatService: ChatService
    private lateinit var chatViewModel: ChatViewModel


    fun getChatViewModel() = chatViewModel

    fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(gsonConverter)
            .build()
    }

    fun createChatService() {
        chatService = retrofitClient.create(
            ChatService::class.java)
    }

    fun initChatViewModel(){
        chatViewModel = ChatViewModel(
            ChatRepository(
                chatService
            )
        )
    }
}