package com.example.newsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Routes.CategoryDetailNews
import com.example.newsapp.presentation.viewmodel.NewsAppViewmodel


@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewsAppViewmodel,
) {
    val selectedCategory = rememberSaveable { mutableStateOf("") }
    val scrollState = rememberLazyListState()
    val searchTerm = rememberSaveable{ mutableStateOf("") }
    val categoryToSearch = rememberSaveable {
            mutableStateOf(arrayListOf("Technology","Politics","Bitcoin","Tesla","Cyber","Hacking"))
    }
    val state = viewModel.state.collectAsState()
    if (state.value.loading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator()//for Loading
        }
    } else if (state.value.error.isBlank().not()) {
        Text(text = state.value.error)
    } else {
        Scaffold(modifier = modifier) {it
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(top = 45.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    value = searchTerm.value, onValueChange = {
                    searchTerm.value = it
                },
                    placeholder = { Text("Search Any News ...") },
                    label = { Text("Search") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = modifier.clickable(enabled = searchTerm.value.isBlank().not(), onClick = {
                            viewModel.getEverything(q = searchTerm.value)
                        }))
                    }
                )
            }
//            Spacer(modifier = modifier.height(5.dp))
            LazyRow(modifier = modifier.fillMaxWidth(),state = scrollState)
            {
                items(categoryToSearch.value){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (it == selectedCategory.value){
                                MaterialTheme.colorScheme.primary
                            }else{
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                        )
                        ,
                        modifier = modifier.padding(horizontal = 5.dp)
                        .clickable{
                            viewModel.getEverything(q = it)
                            selectedCategory.value = it
                        }
                        ) {
                        Text(
                            modifier = modifier.padding(8.dp),
                            text = it,
                            fontSize = 20.sp
                            )
                    }
                }
            }
            val data = state.value.data
            if (data?.articles!!.isEmpty()) {
                Text(text = "No Data Found")
            } else {
                val articles = data.articles.filter { article ->
                    article.title?.contains("Removed") == false
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(articles) { article ->
                        Card(modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(8.dp)
                            .clickable() {
                                navController.navigate(
                                    CategoryDetailNews(
                                        author = article.author,
                                        content = article.content,
                                        description = article.description,
                                        publishedAt = article.publishedAt,
                                        id = article.source?.id,
                                        name = article.source?.name,
                                        title = article.title,
                                        url = article.url,
                                        urlToImage = article.urlToImage
                                    )
                                )
                            }) {
                            Row(modifier = Modifier) {
                                if (article.urlToImage.isNullOrBlank()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                        contentDescription = null
                                    )
                                } else {
                                    AsyncImage(
                                        model = article.urlToImage,
                                        modifier = modifier.size(100.dp).padding(8.dp),
                                        contentDescription = null,
                                        placeholder = painterResource(R.drawable.ic_launcher_background)
                                    )
                                }
                                article.title?.let {
                                    Text(
                                        modifier = modifier.align(Alignment.CenterVertically),
                                        text = it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
}