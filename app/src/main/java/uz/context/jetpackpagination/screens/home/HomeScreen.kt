package uz.context.jetpackpagination.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import uz.context.jetpackpagination.navigation.Screen
import uz.context.jetpackpagination.screens.commons.ListContent

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ListContent(items = getAllImages, navController)
        }
    )
}
