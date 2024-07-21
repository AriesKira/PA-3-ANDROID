package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.model.MessageDto
import com.example.senanas.model.SendMessageDto
import com.example.senanas.model.TicketListDto
import com.example.senanas.network.user.ChatRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel(private val chatRepository: ChatRepository) {
    private val _chat = MutableLiveData<Result<List<MessageDto>?>?>()
    val chat: LiveData<Result<List<MessageDto>?>?> = _chat

    private val _addMessage = MutableLiveData<Boolean>()
    val addMessage: LiveData<Boolean> = _addMessage

    fun getChat(token:String,chatId:Int){
        val call = chatRepository.getChat(token,chatId)
        call.enqueue(object:Callback<List<MessageDto>> {
            override fun onResponse(p0: Call<List<MessageDto>>, p1: Response<List<MessageDto>>) {
                if(p1.isSuccessful){
                    val data = p1.body()
                    _chat.postValue(Result.success(data))
                    println(data)
                }
            }

            override fun onFailure(p0: Call<List<MessageDto>>, p1: Throwable) {
            }

        })
    }


    fun sendMessage(token:String,sendMessageDto: SendMessageDto) {
        val call = chatRepository.sendMessage(token,sendMessageDto)
        call.enqueue(object :Callback<MessageDto> {
            override fun onResponse(p0: Call<MessageDto>, p1: Response<MessageDto>) {
                if(p1.isSuccessful){
                    val data = p1.body()
                    _addMessage.postValue(true)
                }
            }

            override fun onFailure(p0: Call<MessageDto>, p1: Throwable) {
            }

        })
    }
}