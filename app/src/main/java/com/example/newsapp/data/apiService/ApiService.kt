package com.example.newsapp.data.apiService

import com.example.newsapp.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("top-headlines")
    suspend fun getHeadLines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "1e7a79ba171540b5a6a7eccb4dd670c4"
    ) :ApiResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q : String = "us",
        @Query("apiKey") apiKey: String = "1e7a79ba171540b5a6a7eccb4dd670c4"
    ):ApiResponse
}