package com.example.senanas.data

import com.example.senanas.network.user.TicketRepository
import com.example.senanas.network.user.TicketService
import com.example.senanas.viewmodels.TicketListViewModel
import com.example.senanas.viewmodels.TicketViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TicketDataLayerSingleton {
    private lateinit var retrofitClient: Retrofit
    private lateinit var ticketService: TicketService
    private lateinit var ticketViewModel: TicketViewModel
    private lateinit var ticketListViewModel: TicketListViewModel
    fun getTicketViewModel() = ticketViewModel
    fun getTicketListiewModel() = ticketListViewModel

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

    fun createTicketService() {
        ticketService = retrofitClient.create(
            TicketService::class.java)
    }



    fun initTicketViewModel() {
        ticketViewModel = TicketViewModel(
            TicketRepository(
                ticketService,
            ),
        )
    }

    fun initTicketListViewModel() {
        ticketListViewModel = TicketListViewModel(
            TicketRepository(
                ticketService,
            ),
        )
    }


}