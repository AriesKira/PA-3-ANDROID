package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.network.user.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val userRepository: UserRepository) {

    private val _registerResult = MutableLiveData<Result<ResponseRegisterDto>>()
    val registerResult: LiveData<Result<ResponseRegisterDto>> = _registerResult


    fun register(registerDto: RegisterDto) {
        val call = userRepository.register(registerDto)
        call.enqueue(object : Callback<ResponseRegisterDto> {
            override fun onResponse(
                call: Call<ResponseRegisterDto>,
                response: Response<ResponseRegisterDto>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _registerResult.postValue(Result.success(response.body()!!))
                    println("Success: ${responseBody?.message}")
                } else {
                    println("Failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseRegisterDto>, t: Throwable) {
                println("Error: ${t.message}")
            }

        })
    }
}