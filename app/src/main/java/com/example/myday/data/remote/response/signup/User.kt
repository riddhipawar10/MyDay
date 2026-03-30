package com.example.myday.data.remote.response.signup

data class User(
    val email: String,
    val username: String,
    val bio: String,
    val image: String,
    val token: String
)