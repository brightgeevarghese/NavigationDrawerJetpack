package edu.miu.navdrawerdemo.ui

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.miu.navdrawerdemo.ui.components.NavigationDrawerBody
import edu.miu.navdrawerdemo.ui.components.NavigationBarHeader
import edu.miu.navdrawerdemo.data.NavigationItem
import edu.miu.navdrawerdemo.navigation.Screens
import edu.miu.navdrawerdemo.navigation.SetUpNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    //The list of items that will be shown in the drawer
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            route = Screens.Home.route,
            selectedIcon = Icons.Default.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Notification",
            route = Screens.Notification.route,
            selectedIcon = Icons.Default.Person,
            unselectedIcon = Icons.Outlined.Person,
            badgeCount = 3
        ),
        NavigationItem(
            title = "Settings",
            route = Screens.Settings.route,
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val topBarTitle = when (currentDestination?.route) {
        Screens.Home.route -> "Home"
        Screens.Notification.route -> "Notification"
        Screens.Settings.route -> "Settings"
        else -> null
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
//                drawerContainerColor = Color.Gray,
//                drawerContentColor = Color.Red,
                content = {
                    NavigationBarHeader()
                    Spacer(modifier = Modifier.height(12.dp))
                    NavigationDrawerBody(items = navigationItems, currentRoute = navController.currentDestination?.route) {
                        if (it.route == Screens.Notification.route) {

                        }
                        navController.navigate(it.route) {
                            popUpTo(Screens.Home.route) {
                                saveState = true
                            }
                            //launchSingleTop = true: This means that if the destination screen is already at the top of the back stack, the existing instance of the screen will be reused instead of creating a new one. Essentially, it avoids creating a duplicate of the screen if you navigate to it again.
                            //launchSingleTop = false: This means that every time you navigate to a destination, a new instance of that screen is created, even if it's already at the top of the back stack.
                            launchSingleTop = true
                            //ensures that the state of a destination is preserved when it is popped off the back stack. This means that when you navigate away from a screen (or pop it off the stack), its state is saved so that it can be restored later.
                            //Use Case: This is particularly useful if you want to maintain the UI state of a screen when navigating back to it. For example, if you have a form with user-entered data or a list with scroll position, enabling saveState allows the screen to retain that data and position even after navigating away and coming back.
                            restoreState = true
                        }
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = { 
                TopAppBar(
                    title = {
                        if (topBarTitle != null) {
                            Text(text = topBarTitle)
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        open()
                                        //if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "menu"
                            )
                        }
                    }
                )
            }
        ) {
           SetUpNavGraph(navController = navController, modifier = Modifier.padding(it))
        }
    }
}

