package com.example.newsapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.model.Article

@Preview(showBackground = true, showSystemUi = true)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryWiseDetailNews(
    modifier: Modifier = Modifier,
    article: Article = Article(),
//        source = Source(
//            id = "wired",
//            name = "Wired"
//        ),
//
//        author = "Makena Kelly, Joel Khalili",
//        title = "The Crypto Industry Is Helping Trump Pick SEC Chair",
//        description = "The president-elect's transition team is consulting with industry leaders as it vets potential replacements for outgoing chair Gary Gensler, sources tell WIRED.",
//        url = "https://www.wired.com/story/crypto-candidates-front-runners-sec-race",
//        urlToImage = "https://media.wired.com/photos/6745db10e149b18ca8e2b8d8/191:100/w_1280,c_limit/GettyImages-93181618.jpg",
//        publishedAt = "2024-11-26T16:23:34Z",
//        content = "In July, at a bitcoin conference in Nashville, Tennessee, Trump pledged to fire Gensler if reelected, drawing perhaps the most raucous applause of the night. I will appoint an SEC chair who will buil… [+2635 chars]"
//
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "News App",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00BCD4)
                )
            )
        }
    ) {
        Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {

                    Spacer(modifier = modifier.height(20.dp))
                    Text(
                        text = article.title.toString(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_launcher_background)
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Text(
                        text = "Discription :-" + article.description.toString(),
                        style = TextStyle(fontSize = 20  .sp),
                        modifier = modifier
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Text(
                        text = article.content.toString(),
                        style = TextStyle(fontSize = 25.sp),
                        modifier = modifier
                    )
                }
            }
        }
    }
}


