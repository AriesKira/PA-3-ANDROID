package com.example.senanas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.senanas.model.GetUserDto
import com.example.senanas.model.RegisterDto
import com.example.senanas.model.ResponseRegisterDto
import com.example.senanas.model.UpdateUserDto
import com.example.senanas.network.user.UserRepository
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val userRepository: UserRepository) {
    private val _userData = MutableLiveData<Result<GetUserDto?>>()
    val userData: LiveData<Result<GetUserDto?>> = _userData
    fun getUserInfo(token:String) {
        val call = userRepository.getUserInfo(token)
        call.enqueue(object : Callback<GetUserDto> {
            override fun onResponse(p0: Call<GetUserDto>, p1: Response<GetUserDto>) {
                if(p1.isSuccessful) {
                    val data = p1.body()
                    _userData.postValue(Result.success(data))
                }
            }

            override fun onFailure(p0: Call<GetUserDto>, p1: Throwable) {
                println("ERR")
            }

        })
    }

    fun updateUserInfo(token:String,updateUserDto: UpdateUserDto) {
        println(updateUserDto)
        val call = userRepository.updateUser(token,updateUserDto)
        call.enqueue(object:Callback<UpdateUserDto>{
            override fun onResponse(p0: Call<UpdateUserDto>, p1: Response<UpdateUserDto>) {
                if(p1.isSuccessful){
                    val data = p1.body()
                    println(data)
                } else {
                    println("err")
                }
            }

            override fun onFailure(p0: Call<UpdateUserDto>, p1: Throwable) {

            }

        })
    }
}