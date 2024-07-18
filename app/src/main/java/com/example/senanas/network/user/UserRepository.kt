package com.example.senanas.network.user
//import com.example.myfirstapp.model.UserDto
import com.example.senanas.model.GetUserDto
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.model.LoginDto
import com.example.senanas.model.LoginResponseDto
import okhttp3.ResponseBody
import retrofit2.Call

class UserRepository(
    private val userService: UserService
) {
    fun register(registerDto: RegisterDto): Call<ResponseRegisterDto> {
        return userService.register(registerDto)
    }

    fun login(loginDto: LoginDto): Call<LoginResponseDto> {
        return userService.login(loginDto)
    }

    fun getUserInfo(token:String): Call<GetUserDto> {
        return userService.getInfoUser(token)
    }

}