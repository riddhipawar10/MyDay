package com.example.myday.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myday.data.remote.request.login.LoginRequest
import com.example.myday.data.remote.request.signup.SignUpRequest
import com.example.myday.data.remote.response.login.LoginResponse
import com.example.myday.data.remote.response.signup.SignUpResponse
import com.example.myday.ui.repository.MyDayAuthRepositoryImpl
import com.example.myday.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyDayViewModel @Inject constructor(private val repositoryImpl: MyDayAuthRepositoryImpl): ViewModel(){


        private val _signUpResponseLiveData = MutableLiveData<NetworkResult<SignUpResponse>>()
        val signupResponseLiveData : LiveData<NetworkResult<SignUpResponse>>
            get() = _signUpResponseLiveData


        private val _loginResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
        val loginResponseLiveData : LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponseLiveData

    fun signup(signUpRequest: SignUpRequest){
        viewModelScope.launch {
            _signUpResponseLiveData.value = NetworkResult.Loading()
            val response = repositoryImpl.signup(signUpRequest)
            _signUpResponseLiveData.value = response
            Log.d("Hello", "${response.data}")
        }
    }

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            _loginResponseLiveData.value = NetworkResult.Loading()
            val response = repositoryImpl.login(loginRequest)
            _loginResponseLiveData.value =response
        }
    }
}