package com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tuwaiq.workoutassistantapplication.composescreens.MainScreen
import com.tuwaiq.workoutassistantapplication.composescreens.screens.SecondaryScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.SecondaryScreen.route +
            //        "/{name}"
            "?name={name}"
            ,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){
            SecondaryScreen(name = it.arguments?.getString("name")!!)
        }
    }

}