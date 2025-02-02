package com.example.senanas.network.user

import com.example.senanas.model.CategoryDto
import com.example.senanas.model.CreateTicketDto
import com.example.senanas.model.DetailTicketDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.model.TicketListDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TicketService {

    @POST("tickets/createTicket")
    fun createTicket(@Header("Authorization") token: String,@Body createTicketDto: CreateTicketDto): Call<ResponseRegisterDto>

    @GET("tickets/getTickets")
    fun getTickets(@Header("Authorization") token: String): Call<List<TicketListDto>>
    @GET("tickets/getDetailTicket/{id}")
    fun getDetailTicket(@Header("Authorization") token: String,@Path("id") id: Int): Call<DetailTicketDto>
}