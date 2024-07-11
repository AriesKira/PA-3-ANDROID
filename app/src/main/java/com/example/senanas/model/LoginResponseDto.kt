package com.example.senanas.model

data class LoginResponseDto(
    val token : String,
    val user : UserDto

)


data class UserDto(
    val firstname:String,
    val lastname:String,
    val email:String,
    val role:String
)
