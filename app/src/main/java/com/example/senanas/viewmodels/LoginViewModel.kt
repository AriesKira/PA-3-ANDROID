package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.modelDto.LoginDto
import com.example.senanas.modelDto.LoginResponseDto
import com.example.senanas.modelDto.ResponseRegisterDto
import com.example.senanas.network.user.UserRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class LoginViewModel(private val userRepository: UserRepository) {

    private val _loginResult = MutableLiveData<Result<LoginResponseDto?>>()
    val loginResult: LiveData<Result<LoginResponseDto?>> = _loginResult


    fun login(loginDto: LoginDto) {
        val call = this.userRepository.login(loginDto)
        call.enqueue(object:Callback<LoginResponseDto>{
            override fun onResponse(p0: Call<LoginResponseDto>, p1: Response<LoginResponseDto>) {
                if (p1.isSuccessful) {
                    val data = p1.body()
                    _loginResult.postValue(Result.success(data))
                } else {
                    val errorBody = p1.errorBody()?.string()
                    val errorMessage = if (errorBody != null) {
                        try {
                            // Parse the error body to get the message
                            val errorResponse = Gson().fromJson(errorBody, ResponseRegisterDto::class.java)
                            errorResponse.message
                        } catch (e: Exception) {
                            p1.message()
                        }
                    } else {
                        p1.message()
                    }
                    _loginResult.postValue(Result.failure(Exception(errorMessage)))

                }
            }

            override fun onFailure(p0: Call<LoginResponseDto>, p1: Throwable) {

            }

        })
    }

}