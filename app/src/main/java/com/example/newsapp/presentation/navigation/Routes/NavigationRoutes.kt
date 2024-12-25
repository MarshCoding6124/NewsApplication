package com.example.newsapp.presentation.navigation.Routes

import com.example.newsapp.data.model.Source
import kotlinx.serialization.Serializable


    @Serializable
    object HomeScreen
    @Serializable
    data class CategoryDetailNews(
        val author: String? = null,
        val content: String? = null,
        val description: String? = null,
        val publishedAt: String? = null,
        val id: String ?= null,
        val name: String ?= null,
        val title: String? = null,
        val url: String? = null,
        val urlToImage: String? = null
    )

