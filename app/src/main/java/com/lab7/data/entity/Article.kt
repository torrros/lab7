package com.lab7.data.entity

data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Article>
)

data class Article(
    val id: Int,
    val title: String,
    val url: String,
    val image_url: String,
    val news_site: String,
    val summary: String,
    val published_at: String,
    val updated_at: String,
    val featured: Boolean,

)
