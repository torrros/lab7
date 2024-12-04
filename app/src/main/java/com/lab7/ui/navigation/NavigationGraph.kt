package com.lab7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lab7.ui.screens.menu.menu.MenuScreen
import com.lab7.ui.screens.articles.ArticlesScreen
import com.lab7.ui.screens.articles.FavoritesScreen

const val MenuScreen = "menu"
const val ArticlesScreen = "articlesList"
const val SCREEN_FAVORITES = "favorites"
const val SCREEN_ABOUT = "about"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MenuScreen
    ) {
        // Menu Screen
        composable(route = MenuScreen) {
            MenuScreen(
                onArticlesList = { navController.navigate( ArticlesScreen) },
                onFavorites = { navController.navigate(SCREEN_FAVORITES) },
                onAbout = { navController.navigate(SCREEN_ABOUT) }
            )
        }

        // Articles List Screen
        composable(route = ArticlesScreen) {
            ArticlesScreen(onArticleSelected = { articleId ->
            })
        }

        composable(route = SCREEN_FAVORITES) {
         FavoritesScreen(onFavouriteScreen = { articleId ->
            })
        }
        /*
        // About Screen
        composable(route = SCREEN_ABOUT) {
            AboutScreen()
        }
         */
    }
}
