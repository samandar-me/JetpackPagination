package uz.context.jetpackpagination.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import uz.context.jetpackpagination.screens.detail.DetailScreen
import uz.context.jetpackpagination.screens.home.HomeScreen
import uz.context.jetpackpagination.screens.search.SearchScreen


@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = "${Screen.DetailScreen.route}/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString("id") ?: "FHhbHW4vFxc"
            DetailScreen(id = id, navController = navController)
        }
    }
}