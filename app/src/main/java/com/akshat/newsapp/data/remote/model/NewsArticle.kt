package com.akshat.newsapp.data.remote.model

import com.akshat.newsapp.data.remote.model.Article

data class NewsArticle(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)