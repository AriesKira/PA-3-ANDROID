package com.example.senanas.viewmodels

import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.network.user.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val userRepository: UserRepository) {
    fun test(){
        val call = userRepository.register(RegisterDto(email = "cedced@gmail.com", firstname = "cedric", lastname = "cedric", password = "zeaze"))
        call.enqueue(object : Callback<ResponseRegisterDto> {
            override fun onResponse(
                call: Call<ResponseRegisterDto>,
                response: Response<ResponseRegisterDto>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
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