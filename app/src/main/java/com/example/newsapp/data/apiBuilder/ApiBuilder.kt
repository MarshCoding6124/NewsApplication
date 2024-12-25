package com.example.newsapp.data.apiBuilder

import com.example.newsapp.data.apiService.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    //object is use for create one time object and also object class variable declare only 1 time in runmode
    fun retrofitObject(): ApiService {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}