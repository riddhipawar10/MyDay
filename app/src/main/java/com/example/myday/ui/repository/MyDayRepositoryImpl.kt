package com.example.myday.ui.repository

import com.example.myday.data.UserApi
import com.example.myday.data.remote.response.ArticleResponse
import com.example.myday.domain.repository.MyDayRepository
import com.example.myday.utils.NetworkResult
import javax.inject.Inject

class MyDayRepositoryImpl @Inject constructor(val api: UserApi): MyDayRepository {
    override suspend fun getPost(): NetworkResult<ArticleResponse> {
        return try {
            val response = api.getPost()
            if (response.isSuccessful){
                NetworkResult.Success(response.body())
            } else{
                NetworkResult.Failed("Something went wrong")
            }
        }catch (e: Exception){
            NetworkResult.Failed("Network error occurred")
        }

    }
}