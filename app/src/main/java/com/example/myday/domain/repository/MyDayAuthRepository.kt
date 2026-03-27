package com.example.myday.domain.repository

import com.example.myday.data.remote.request.login.LoginRequest
import com.example.myday.data.remote.request.signup.SignUpRequest
import com.example.myday.data.remote.response.login.LoginResponse
import com.example.myday.data.remote.response.signup.SignUpResponse
import com.example.myday.utils.NetworkResult


interface MyDayAuthRepository {
    suspend fun signup(signUpRequest: SignUpRequest): NetworkResult<SignUpResponse>
    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse>
}