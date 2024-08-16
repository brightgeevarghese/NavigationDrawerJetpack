package edu.miu.navdrawerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.miu.navdrawerdemo.navigation.NavigationDrawerApp
import edu.miu.navdrawerdemo.ui.theme.NavDrawerDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavDrawerDemoTheme {
                val navController = rememberNavController()
                NavigationDrawerApp(navController = navController)
            }
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    NavigationDrawerApp(navController = navController)
}