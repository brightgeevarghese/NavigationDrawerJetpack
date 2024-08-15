package edu.miu.navdrawerdemo.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Notification : Screens("notification")
    data object Settings : Screens("settings")
}