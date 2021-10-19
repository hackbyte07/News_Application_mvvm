package com.hackbyte.newsapplication.ui.views.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hackbyte.newsapplication.ui.MainViewModel
import com.hackbyte.newsapplication.ui.views.components.DetailScreen
import com.hackbyte.newsapplication.ui.views.components.HomeScreen


@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = Navigation.HomeScreen.route) {

        composable(Navigation.HomeScreen.route) {
            HomeScreen(navController, mainViewModel)
        }
        composable(
            route = Navigation.DetailScreen.route + "/{url}",
            arguments = listOf(navArgument("url")
            {
                type = NavType.StringType
                defaultValue = ""
            }

            )
        ) { entry ->
            DetailScreen(url = entry.arguments?.getString("url")!!)
        }
    }

}