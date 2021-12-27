package com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav

import kotlin.time.measureTime

sealed class Screen(val route:String){
    object WorkoutListScreen: Screen("workout_list_screen")
    object AddEditWorkoutScreen: Screen ("workout_screen")
    object ExerciseListScreen: Screen("exercise_list_screen")

//    fun withArgs(vararg args : String):String{
//        return buildString {
//            append(route)
//            args.forEach {
//             append("/$it")
//            }
//        }
//    }
}
