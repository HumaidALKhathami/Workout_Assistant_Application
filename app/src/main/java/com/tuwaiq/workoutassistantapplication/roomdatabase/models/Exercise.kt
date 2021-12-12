package com.tuwaiq.workoutassistantapplication.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseID: Int = 0 ,
    var workoutName: String = "",
    var duration: Int = 0
        )