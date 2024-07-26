package com.example.senanas.network.user

import com.example.senanas.modelDto.MessageDto
import com.example.senanas.modelDto.SendMessageDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {

    @GET("chat/{id}/messages")
    fun getChat(@Header("Authorization") token: String,@Path("id") id: Int): Call<List<MessageDto>>

    @POST("chat/sendMessageAndroid")
    fun sendMessage(@Header("Authorization") token: String,@Body sendMessageDto: SendMessageDto): Call<MessageDto>


}