package com.example.myday.utils

import android.os.Message

sealed class NetworkResult<T>(val data:T? = null, val message: String?=null) {
    class Success<T>(data: T?): NetworkResult<T>(data)
    class Failed<T>(message: String?): NetworkResult<T>(message=message)
    class Loading<T>(): NetworkResult<T>()

}


