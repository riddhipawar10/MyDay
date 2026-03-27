package com.example.myday.ui.repository

import android.util.Log
import com.example.myday.data.remote.Auth
import com.example.myday.data.remote.request.login.LoginRequest
import com.example.myday.data.remote.request.signup.SignUpRequest
import com.example.myday.data.remote.response.login.LoginResponse
import com.example.myday.data.remote.response.signup.SignUpResponse
import com.example.myday.domain.repository.MyDayAuthRepository
import com.example.myday.utils.NetworkResult
import javax.inject.Inject
import java.lang.Exception

class MyDayAuthRepositoryImpl @Inject constructor(private val api: Auth): MyDayAuthRepository{

//    private val _signUpResponseLiveData = MutableLiveData<NetworkResult<SignUpResponse>>()
//    val signupResponseLiveData : LiveData<NetworkResult<SignUpResponse>>
//        get() = _signUpResponseLiveData

    override suspend fun signup(signUpRequest: SignUpRequest): NetworkResult<SignUpResponse> {

        return try {
            val response = api.signUp(signUpRequest)
            if(response.isSuccessful){
                Log.d("MyAp", "${response.body()}")
                NetworkResult.Success(response.body())
            }else{
                Log.d("MyAp", "${response.body()}")
                NetworkResult.Failed("Failed to sign up.")
            }
        }catch (e: Exception){
            NetworkResult.Failed("Network error occurred.")
        }
    }

    override suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> {
        return try {
            val response = api.login(loginRequest)
            if(response.isSuccessful){
                NetworkResult.Success(response.body())
            }else{
                NetworkResult.Failed("Failed to login in")
            }
        }catch (e: kotlin.Exception){
            NetworkResult.Failed("Network error occurred.")
        }
    }

}