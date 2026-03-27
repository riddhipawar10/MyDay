package com.example.myday.data

import com.example.myday.data.remote.response.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("articles")
    suspend fun getPost(): Response<ArticleResponse>
}