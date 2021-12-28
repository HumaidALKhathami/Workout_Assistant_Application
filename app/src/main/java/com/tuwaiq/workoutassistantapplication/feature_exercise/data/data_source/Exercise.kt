package com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseID: Int = 0,
    var exerciseName: String = "",
    var duration: Int = 0
        )