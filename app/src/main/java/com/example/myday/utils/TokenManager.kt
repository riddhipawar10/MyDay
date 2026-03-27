package com.example.myday.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var sharedPreferences = context.getSharedPreferences("MYDAY_TOKEN", Context.MODE_PRIVATE)

    fun saveToken(token: String){
        val editor = sharedPreferences.edit()
        editor.putString("USER_TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("USER_TOKEN",null)
    }
}