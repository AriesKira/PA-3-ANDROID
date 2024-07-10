package com.example.senanas.network.user
//import com.example.myfirstapp.model.UserDto
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import retrofit2.Call
import retrofit2.http.GET

class UserRepository(
    private val userService: UserService
) {
    fun register(registerDto: RegisterDto): Call<ResponseRegisterDto> {
        return userService.register(registerDto)
    }

}