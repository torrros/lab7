package com.lab7.ui.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab7.data.ServerApi
import com.lab7.data.entity.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.lab7.data.dao.ArticleDao
import com.lab7.data.entity.Article
import com.lab7.data.entity.ArticleEntity

// ArticlesScreenViewModel
class ArticlesScreenViewModel(
    private val serverApi: ServerApi,
    private val articleDao: ArticleDao
) : ViewModel() {

    private val _articlesStateFlow = MutableStateFlow<ApiResponse?>(null)
    val articlesStateFlow: StateFlow<ApiResponse?>
        get() = _articlesStateFlow

    init {
        fetchArticles()
    }
    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = serverApi.getArticles()
                _articlesStateFlow.value = response
            } catch (e: Exception) {
                Log.e("ArticlesAPI", "Error fetching articles: ${e.message}")
            }
        }
    }

    fun saveToFavorites(article: Article) {
        viewModelScope.launch {
            val articleEntity = ArticleEntity(
                id = article.id,
                title = article.title,
                url = article.url,
                imageUrl = article.image_url,
                newsSite = article.news_site,
                summary = article.summary,
                publishedAt = article.published_at,
                updatedAt = article.updated_at,
                featured = article.featured
            )
            articleDao.insertArticles(listOf(articleEntity))
        }

    }

}

