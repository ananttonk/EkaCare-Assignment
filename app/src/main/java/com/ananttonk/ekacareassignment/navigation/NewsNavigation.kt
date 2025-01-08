package com.ananttonk.ekacareassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ananttonk.ekacareassignment.model.News
import com.ananttonk.ekacareassignment.ui.screen.HomeScreen
import com.ananttonk.ekacareassignment.ui.screen.NewsDetails
import com.ananttonk.ekacareassignment.ui.screen.SavedArticlesScreen

@Composable
fun NewsNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationScreen.HomeScreen.name) {
        composable(route = NavigationScreen.HomeScreen.name) {
            HomeScreen(
                openNewsDetails = { source ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("source", source)
                    navController.navigate(NavigationScreen.NewsDetails.name)
                },
                openSavedArticles = {
                    navController.navigate(NavigationScreen.SavedArticlesScreen.name)
                }
            )
        }
        composable(
            route = NavigationScreen.NewsDetails.name,
        ) {
            val article =
                navController.previousBackStackEntry?.savedStateHandle?.get<News.Source>("source")
            NewsDetails(news = article) { navController.popBackStack() }
        }
        composable(
            route = NavigationScreen.SavedArticlesScreen.name,
        ) {
            SavedArticlesScreen(
                onBack = { navController.popBackStack() },
                openNewsDetails = { source ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("source", source)
                    navController.navigate(NavigationScreen.NewsDetails.name)
                }
            )
        }
    }
}