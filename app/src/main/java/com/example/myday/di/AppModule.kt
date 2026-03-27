package com.example.myday.di

import android.annotation.SuppressLint
import com.example.myday.data.UserApi
import com.example.myday.data.remote.Auth
import com.example.myday.utils.AuthInterceptor
import com.example.myday.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun providerRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @SuppressLint("SuspiciousIndentation")
    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideOkHttpWithAuth(authInterceptor: AuthInterceptor): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor).build()
    }
    @Singleton
    @Provides
    fun provideAuthApi(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):Auth {
        return retrofitBuilder.client(okHttpClient).build().create(Auth::class.java)
    }

    @Singleton
    @Provides
    fun userApi(retrofitBuilder: Retrofit.Builder, @Named("auth_token") okHttpClient: OkHttpClient): UserApi{
        return retrofitBuilder.client(okHttpClient).build().create(UserApi::class.java)
    }



}