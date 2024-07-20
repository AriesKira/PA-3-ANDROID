package com.example.senanas.network.user

import com.example.senanas.model.CreateTicketDto
import com.example.senanas.model.ResponseRegisterDto
import retrofit2.Call

class TicketRepository(private val ticketService: TicketService) {
    fun createTicket(token:String,createTicketDto: CreateTicketDto): Call<ResponseRegisterDto> {
        return ticketService.createTicket(token,createTicketDto)
    }
}