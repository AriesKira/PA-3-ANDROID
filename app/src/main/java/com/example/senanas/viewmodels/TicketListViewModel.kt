package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.modelDto.TicketListDto
import com.example.senanas.network.user.TicketRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TicketListViewModel(private val ticketRepository: TicketRepository) {
    private val _tickets = MutableLiveData<Result<List<TicketListDto>?>?>()
    val tickets: LiveData<Result<List<TicketListDto>?>?> = _tickets
    fun getTicket(token:String) {
        val call = ticketRepository.getTickets(token)
        call.enqueue(object:Callback<List<TicketListDto>> {
            override fun onResponse(
                p0: Call<List<TicketListDto>>,
                p1: Response<List<TicketListDto>>
            ) {
                if(p1.isSuccessful) {
                    val data = p1.body()

                    println(data)
                    _tickets.postValue(Result.success(data))
                }

            }

            override fun onFailure(p0: Call<List<TicketListDto>>, p1: Throwable) {

            }

        })
    }

}