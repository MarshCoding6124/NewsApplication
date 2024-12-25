package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.navigation.AppNavigation
import com.example.newsapp.presentation.viewmodel.NewsAppViewmodel
import com.example.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<NewsAppViewmodel>()
            NewsAppTheme {
                    Box(modifier = Modifier.fillMaxSize()){
                            AppNavigation (viewModel = viewModel)
                    }

                }
            }
        }
    }
