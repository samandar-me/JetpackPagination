package uz.context.jetpackpagination.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object DetailScreen: Screen("detail_screen")
}
