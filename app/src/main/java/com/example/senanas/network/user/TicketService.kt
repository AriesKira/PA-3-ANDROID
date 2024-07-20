package com.example.senanas.network.user

import com.example.senanas.model.CreateTicketDto
import com.example.senanas.model.ResponseRegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TicketService {

    @POST("tickets/createTicket")
    fun createTicket(@Header("Authorization") token: String,@Body createTicketDto: CreateTicketDto): Call<ResponseRegisterDto>
}