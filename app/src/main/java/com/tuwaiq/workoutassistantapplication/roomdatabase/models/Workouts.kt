package com.tuwaiq.workoutassistantapplication.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workouts(
    @PrimaryKey(autoGenerate = true)
    val workoutID: Int = 0,
    var workoutsName:String = "",
    var exercises:List<Exercise> = emptyList()
)