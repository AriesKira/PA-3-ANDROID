package com.example.senanas.model

data class UpdateUserDto(
    val email: String?,
    val firstname: String?,
    val lastname: String?,
    val address: String? = null,
    val zipcode: String? = null,
    val city: String? = null
)
