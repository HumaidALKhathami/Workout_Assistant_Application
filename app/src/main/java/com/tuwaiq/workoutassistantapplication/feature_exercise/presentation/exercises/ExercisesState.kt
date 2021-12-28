package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises

import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise

data class ExercisesState(
    val workoutName: String ="",
    val exercises: List<Exercise> = emptyList()
)
