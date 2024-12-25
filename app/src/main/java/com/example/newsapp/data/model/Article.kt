package com.example.newsapp.data.model

data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)

//fun test(){
//    Article(
//        source = Source(
//            id = "fortune",
//            name = "Fortune",
//            author = "Emma Hinchliffe,Paige McGlauflin",
//        )
//    )
//}


//{
//    "source": {
//    "id": "fortune",
//    "name": "Fortune"
//},
//    "author": "Emma Hinchliffe, Paige McGlauflin",
//    "title": "Why a former SoftBank partner is tackling mid-career drop-off for working mothers",
//    "description": "Former SoftBank partner and Facebook India director Kirthiga Reddy is the cofounder of Laddrr, a resource hub for working mothers aiming to prevent mid-career drop-off.",
//    "url": "https://fortune.com/2022/06/01/former-softbank-partner-tackling-mid-career-drop-off-for-working-mothers/",
//    "urlToImage": "https://content.fortune.com/wp-content/uploads/2022/05/Kirthiga1.jpg?resize=1200,600",
//    "publishedAt": "2022-06-01T13:22:34Z",
//    "content": "Skip to Content"
//}