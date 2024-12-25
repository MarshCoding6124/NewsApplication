package com.example.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source
import com.example.newsapp.presentation.navigation.Routes.CategoryDetailNews
import com.example.newsapp.presentation.navigation.Routes.HomeScreen
import com.example.newsapp.presentation.screens.CategoryWiseDetailNews
import com.example.newsapp.presentation.screens.HomeScreenUI
import com.example.newsapp.presentation.viewmodel.NewsAppViewmodel

@Composable
fun AppNavigation(modifier: Modifier = Modifier,viewModel: NewsAppViewmodel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreenUI(modifier = modifier,navController=navController,viewModel = viewModel)
        }
        composable<CategoryDetailNews> {
            val categoryDetailNews = it.toRoute<CategoryDetailNews>()
            val article = Article(
                author = categoryDetailNews.author,
                content = categoryDetailNews.content,
                description = categoryDetailNews.description,
                publishedAt = categoryDetailNews.publishedAt,
                source = Source(
                    id = categoryDetailNews.id,
                    name = categoryDetailNews.name
                ),
                title = categoryDetailNews.title,
                url = categoryDetailNews.url,
                urlToImage = categoryDetailNews.urlToImage
            )
            CategoryWiseDetailNews(article = article, navController=navController)
        }
    }
}