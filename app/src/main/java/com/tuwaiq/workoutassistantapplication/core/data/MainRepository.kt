package com.tuwaiq.workoutassistantapplication.core.data

import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository

data class MainRepository(
    val workoutRepository: WorkoutRepository,
    val exerciseRepository: ExerciseRepository
)
