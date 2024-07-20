package com.example.senanas.data

import com.example.senanas.network.user.TicketRepository
import com.example.senanas.network.user.TicketService
import com.example.senanas.network.user.UserRepository
import com.example.senanas.network.user.UserService
import com.example.senanas.viewmodels.HomeViewModel
import com.example.senanas.viewmodels.LoginViewModel
import com.example.senanas.viewmodels.ProfileViewModel
import com.example.senanas.viewmodels.RegisterViewModel
import com.example.senanas.viewmodels.TicketViewModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserDataLayerSingleton {

    private lateinit var retrofitClient: Retrofit
    private lateinit var userService: UserService

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var homeViewModel: HomeViewModel


    fun getRegisterViewModel() = registerViewModel
    fun getLoginViewModelViewModel() = loginViewModel

    fun getProfileViewModel() = profileViewModel
    fun getHomeViewModel() = homeViewModel


    // Setup HTTP client + services
    fun createRetrofitClient() {
        val gsonConverter =
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        retrofitClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
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
            ),
        )
    }

    fun initLoginViewModel() {
        loginViewModel = LoginViewModel(
            UserRepository(
                userService,
            ),
        )
    }

    fun initProfileViewModel() {
        profileViewModel = ProfileViewModel(
            UserRepository(
                userService,
            ),
        )
    }

    fun initHomeViewModel() {
        homeViewModel = HomeViewModel(
            UserRepository(
                userService,
            ),
        )
    }




}