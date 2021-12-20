package com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tuwaiq.workoutassistantapplication.composescreens.screens.WorkoutListScreen
import com.tuwaiq.workoutassistantapplication.composescreens.screens.SecondaryScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutListScreen.route
    ) {
        composable(route = Screen.WorkoutListScreen.route){
            WorkoutListScreen(navController = navController)
        }

        composable(
            route = Screen.ExerciseListScreen.route +
                    "/{name}"
            //"?name={name}"
            ,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            SecondaryScreen(name = it.arguments?.getString("name")!!)
        }
    }

}