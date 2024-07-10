package com.example.senanas.network.user
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("auth/signup")
    fun register(@Body registerDto: ResponseRegisterDto): Call<ResponseRegisterDto>
}