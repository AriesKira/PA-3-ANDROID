package com.example.senanas.network.user
//import com.example.myfirstapp.model.UserDto
import com.example.senanas.modelDto.CategoryDto
import com.example.senanas.modelDto.GetUserDto
import com.example.senanas.modelDto.RegisterDto
import com.example.senanas.modelDto.ResponseRegisterDto
import com.example.senanas.modelDto.LoginDto
import com.example.senanas.modelDto.LoginResponseDto
import com.example.senanas.modelDto.UpdateUserDto
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

    fun updateUser(token:String,updateUserDto: UpdateUserDto): Call<UpdateUserDto> {
        return userService.updateUser(token,updateUserDto)
    }

    fun getUserInfo(token:String): Call<GetUserDto> {
        return userService.getInfoUser(token)
    }

    fun getCategory(token:String): Call<List<CategoryDto>> {
        return userService.getCategory(token)
    }

}