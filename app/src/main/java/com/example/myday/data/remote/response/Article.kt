package com.example.myday.data.remote.response

data class Article(
    val title: String = "",
    val description: String = "",
    val body: String = "",
    val userId: String = "",
    val favorited: Boolean = false,
    val favoritesCount: Int = 0,
    val slug: String = "",
    val tagList: List<String> = emptyList(),
    val author: Author = Author()
)
