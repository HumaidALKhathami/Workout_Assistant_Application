package com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tuwaiq.workoutassistantapplication.composescreens.screens.WorkoutListScreen
import com.tuwaiq.workoutassistantapplication.composescreens.screens.SecondaryScreen
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout.components.AddEditWorkoutScreen

@ExperimentalAnimationApi
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
            route = Screen.AddEditWorkoutScreen.route +
                    //"/{id}"
            "?workoutId={workoutId}"
            ,
            arguments = listOf(
                navArgument("workoutId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            AddEditWorkoutScreen(navController = navController)
        }
    }

}