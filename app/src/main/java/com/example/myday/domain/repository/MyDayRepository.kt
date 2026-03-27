package com.example.myday.domain.repository

import com.example.myday.data.remote.response.ArticleResponse
import com.example.myday.utils.NetworkResult

interface MyDayRepository {
    suspend fun getPost(): NetworkResult<ArticleResponse>
}