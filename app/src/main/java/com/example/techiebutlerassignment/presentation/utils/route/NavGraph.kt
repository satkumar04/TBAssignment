package com.example.techiebutlerassignment.presentation.utils.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.techiebutlerassignment.presentation.DataScreen
import com.example.techiebutlerassignment.presentation.DetailsScreen



@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route,
    ) {

        composable(route = AppScreen.HomeScreen.route) {
            DataScreen(navController)
        }


        composable("detail/{data}") { navBackStackEntry ->
            val dataString = navBackStackEntry.arguments?.getString("data")
            /* We check if it's not null */
            dataString?.let { data ->
                DetailsScreen(jasonData = dataString)
            }
        }
    }
}