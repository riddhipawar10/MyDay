package com.example.myday.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myday.ui.repository.MyDayRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myday.data.remote.response.ArticleResponse
import com.example.myday.utils.NetworkResult
import kotlinx.coroutines.launch


@HiltViewModel
class MyDayUserViewModel @Inject constructor(private val repositoryImpl: MyDayRepositoryImpl) : ViewModel() {

    private val _getPostData = MutableLiveData<NetworkResult<ArticleResponse>>()
    val getPostData : LiveData<NetworkResult<ArticleResponse>>
        get() = _getPostData


    fun getPost(){
        viewModelScope.launch {
            val response = repositoryImpl.getPost()
            _getPostData.value = response
        }
    }
}

