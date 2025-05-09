package com.example.fittrack_mobile.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fittrack_mobile.HomeScreen
import com.example.fittrack_mobile.data.FoodScreen
import com.example.fittrack_mobile.reminders.ReminderDetailScreen
import com.example.fittrack_mobile.data.ActivityScreen
import com.example.fittrack_mobile.data.ChatScreen
import com.example.fittrack_mobile.screen.AuthScreen
import com.example.fittrack_mobile.screen.SignupScreen
import com.example.fittrack_mobile.screen.Startcreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            Startcreen(navController) // flujo de autenticación
        }

        composable("auth") {
            AuthScreen(navController) // flujo de autenticación
        }

        composable("signup") {
            SignupScreen(navController) // flujo de autenticación
        }

        composable("home") {
            HomeScreen(navController)
        }
        composable("food") {
            FoodScreen(navController)
        }
        composable("reminderDetail") {
            ReminderDetailScreen()
        }
        composable("activity") {
            ActivityScreen(navController)
        }
        composable("chat") {
            ChatScreen(navController)
        }
    }
}
