package com.example.senanas.network.user
import com.example.senanas.model.GetUserDto
import com.example.senanas.model.LoginDto
import com.example.senanas.model.LoginResponseDto
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {

    @POST("auth/signup")
    fun register(@Body registerDto: RegisterDto): Call<ResponseRegisterDto>

    @POST("auth/signin")
    fun login(@Body loginDto: LoginDto): Call<LoginResponseDto>

    @GET("auth/me")
    fun getInfoUser(@Header("Authorization") token: String): Call<GetUserDto>
}