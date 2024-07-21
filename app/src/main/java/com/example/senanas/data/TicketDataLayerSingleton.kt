package com.example.senanas.data

import com.example.senanas.network.user.TicketRepository
import com.example.senanas.network.user.TicketService
import com.example.senanas.viewmodels.DetailTicketViewModel
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
    private lateinit var detailTicketViewModel: DetailTicketViewModel
    fun getTicketViewModel() = ticketViewModel
    fun getTicketListViewModel() = ticketListViewModel
    fun getDetailTicketListViewModel() = detailTicketViewModel

    fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("http://149.202.46.116:3000/")
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

    fun initDetailTicketListViewModel() {
        detailTicketViewModel = DetailTicketViewModel(
            TicketRepository(
                ticketService,
            ),
        )
    }


}