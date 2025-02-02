package com.example.senanas.network.user

import com.example.senanas.model.CreateTicketDto
import com.example.senanas.model.DetailTicketDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.model.TicketListDto
import retrofit2.Call

class TicketRepository(private val ticketService: TicketService) {
    fun createTicket(token:String,createTicketDto: CreateTicketDto): Call<ResponseRegisterDto> {
        return ticketService.createTicket(token,createTicketDto)
    }

    fun getTickets(token:String): Call<List<TicketListDto>> {
        return ticketService.getTickets(token)
    }

    fun getDetailTicket(token:String,id:Int):Call<DetailTicketDto> {
        return ticketService.getDetailTicket(token,id)
    }
}