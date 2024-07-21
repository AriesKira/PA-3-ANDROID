package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.model.DetailTicketDto
import com.example.senanas.network.user.TicketRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTicketViewModel(private val ticketRepository: TicketRepository) {

    private val _detailTicket = MutableLiveData<Result<DetailTicketDto?>>()
    val detailTicket: LiveData<Result<DetailTicketDto?>> = _detailTicket

    fun getDetailTicket(token:String,id:Int){
        val call = ticketRepository.getDetailTicket(token,id)
        call.enqueue(object : Callback<DetailTicketDto> {

            override fun onResponse(p0: Call<DetailTicketDto>, p1: Response<DetailTicketDto>) {
                if (p1.isSuccessful) {
                    val data = p1.body()
                    println(data)
                    _detailTicket.postValue(Result.success(data))
                }
            }

            override fun onFailure(p0: Call<DetailTicketDto>, p1: Throwable) {

            }
        })
    }
}