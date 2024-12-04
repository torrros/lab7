package com.lab7.ui.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.lab7.data.ServerApi
import com.lab7.data.dao.ArticleDao
import com.lab7.data.entity.ArticleEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesScreenViewModel(
    private val articleDao: ArticleDao
) : ViewModel() {

    val favoriteArticlesFlow: StateFlow<List<ArticleEntity>> =
        articleDao.getAllArticles()
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Method to delete an article
    fun deleteArticle(article: ArticleEntity) {
        viewModelScope.launch {
            articleDao.deleteArticle(article)
        }
    }
}
