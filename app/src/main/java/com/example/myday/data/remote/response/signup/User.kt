package com.example.myday.data.remote.response.signup

data class User(
    val bio: Any,
    val email: String,
    val image: String,
    val token: String,
    val username: String
)