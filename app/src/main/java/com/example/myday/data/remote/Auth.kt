package com.example.myday.data.remote


import com.example.myday.data.remote.request.login.LoginRequest
import com.example.myday.data.remote.request.signup.SignUpRequest
import com.example.myday.data.remote.response.login.LoginResponse
import com.example.myday.data.remote.response.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface Auth {

    @POST("api/users")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @POST("api/users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


}