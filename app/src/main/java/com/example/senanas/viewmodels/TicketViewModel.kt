package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.model.CreateTicketDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.network.user.TicketRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TicketViewModel(private val ticketRepository: TicketRepository) {
    private val _createTicketResult = MutableLiveData<Result<ResponseRegisterDto>>()
    val createTicketResult: LiveData<Result<ResponseRegisterDto>> = _createTicketResult
    fun createTicket(token:String,createTicketDto: CreateTicketDto){
        val call = ticketRepository.createTicket(token,createTicketDto)
        call.enqueue(object : Callback<ResponseRegisterDto> {

            override fun onResponse(p0: Call<ResponseRegisterDto>, p1: Response<ResponseRegisterDto>) {
                if(p1.isSuccessful){
                    val data = p1.body()
                    data?.let {
                        _createTicketResult.postValue(Result.success(data))
                    }

                }

            }

            override fun onFailure(p0: Call<ResponseRegisterDto>, p1: Throwable) {

            }
        })
    }
}

