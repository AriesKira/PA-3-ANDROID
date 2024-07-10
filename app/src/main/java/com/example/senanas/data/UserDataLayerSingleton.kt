package com.example.myfirstapp.data

import android.content.Context
import com.example.senanas.network.user.UserRepository
import com.example.senanas.network.user.UserService
import com.example.senanas.viewmodels.RegisterViewModel
//import com.example.myfirstapp.model.TodoModel
//import com.example.myfirstapp.network.TodoServices
//import com.example.myfirstapp.network.TodosRepository
//import com.example.myfirstapp.viewmodels.TodoViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserDataLayerSingleton {

    private lateinit var retrofitClient: Retrofit
    private lateinit var userService: UserService
    private lateinit var registerViewModel: RegisterViewModel


    fun getRegisterViewModel() = registerViewModel


    // Setup HTTP client + services
    fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(gsonConverter)
            .build()
    }


    fun createTodoService() {
        userService = retrofitClient.create(UserService::class.java)
    }

    fun initViewModel() {
        registerViewModel = RegisterViewModel(
            UserRepository(
                userService,
            )
        )
    }


}