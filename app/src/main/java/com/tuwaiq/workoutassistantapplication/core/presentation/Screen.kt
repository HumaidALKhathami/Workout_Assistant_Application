package com.tuwaiq.workoutassistantapplication.core.presentation

sealed class Screen(val route:String){
    object WorkoutListScreen: Screen("workout_list_screen")
    object AddEditWorkoutScreen: Screen("workout_screen")
    object ExerciseListScreen: Screen("exercise_list_screen")
    object AddEditExerciseScreen: Screen("exercise_screen")
    object PersonalProfileScreen:Screen("personal_profile")

//    fun withArgs(vararg args : String):String{
//        return buildString {
//            append(route)
//            args.forEach {
//             append("/$it")
//            }
//        }
//    }
}
