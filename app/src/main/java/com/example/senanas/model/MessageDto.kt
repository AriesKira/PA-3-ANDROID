package com.example.senanas.model

data class MessageDto(
    val message:String,
    val date: String,
    val isFromSupport:Boolean,
    val sender:String
)
