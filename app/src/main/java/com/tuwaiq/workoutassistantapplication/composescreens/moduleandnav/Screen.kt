package com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav

import kotlin.time.measureTime

sealed class Screen(val route:String){
    object MainScreen: Screen("main_screen")
    object SecondaryScreen: Screen("secondary_screen")

    fun withArgs(vararg args : String):String{
        return buildString {
            append(route)
            args.forEach {
             append("/$it")
            }
        }
    }
}
