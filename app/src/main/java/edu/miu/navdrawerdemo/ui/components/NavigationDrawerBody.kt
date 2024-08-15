package edu.miu.navdrawerdemo.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import edu.miu.navdrawerdemo.data.NavigationItem

@Composable
fun NavigationDrawerBody(
    items: List<NavigationItem>,
    currentRoute: String?,
    onItemClick: (NavigationItem) -> Unit
) {
    items.forEach { item ->
        NavigationDrawerItem(
            label = { Text(text = item.title) },
            selected = currentRoute == item.route,
            onClick = {
                onItemClick(item) // Executes the lambda
            },
            icon = {
                Icon(
                    imageVector = if (currentRoute == item.route)
                        item.selectedIcon
                    else
                        item.unselectedIcon,
                    contentDescription = item.title
                )
            },
            badge = {
                item.badgeCount?.let {
                    Text(text = it.toString())
                }
            }
        )
    }
}