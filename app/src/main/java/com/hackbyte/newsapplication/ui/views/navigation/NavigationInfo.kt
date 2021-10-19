package com.hackbyte.newsapplication.ui.views.navigation


sealed class Navigation(val route: String) {
    object HomeScreen : Navigation("home_screen")
    object DetailScreen : Navigation("detail_screen")
}