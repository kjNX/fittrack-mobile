package com.example.fittrack_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fittrack_mobile.HomeScreen
import com.example.fittrack_mobile.reminders.ReminderDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("reminderDetail") {
            ReminderDetailScreen()
        }
    }
}