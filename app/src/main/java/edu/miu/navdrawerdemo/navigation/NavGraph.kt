package edu.miu.navdrawerdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.miu.navdrawerdemo.ui.screens.HomeScreen
import edu.miu.navdrawerdemo.ui.screens.NotificationScreen
import edu.miu.navdrawerdemo.ui.screens.SettingsScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = Screens.Notification.route) {
            NotificationScreen(modifier = modifier)
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen(modifier = modifier)
        }
    }
}