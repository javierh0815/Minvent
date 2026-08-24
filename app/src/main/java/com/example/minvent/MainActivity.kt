package com.example.minvent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minvent.screens.HomeScreen
import com.example.minvent.screens.LoginScreen
import com.example.minvent.screens.RecoverPassScreen
import com.example.minvent.screens.RegisterScreen
import com.example.minvent.ui.theme.MinventTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController)
                }
                composable("register"){
                    RegisterScreen(navController)
                }
                composable("recover"){
                    RecoverPassScreen(navController)
                }
                composable("home") {
                    HomeScreen(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MinventTheme {
        val previewNavController = rememberNavController()
        LoginScreen(navController = previewNavController)
    }
}