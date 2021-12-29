package com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val workoutID: Int = 0,
    var workoutName:String = "",
    var exercises: List<Int> = emptyList()
)