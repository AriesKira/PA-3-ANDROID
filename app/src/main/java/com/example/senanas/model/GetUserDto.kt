package com.example.senanas.model

data class GetUserDto(
    val email: String,
    val firstname: String,
    val lastname: String,
    val address: String?,
    val zipcode: Int?,
    val city: String?
)