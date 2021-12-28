package com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case.GetWorkoutUseCase

data class ExerciseUseCases(
    val getExercise: GetExerciseUseCase,
    val deleteExercise: DeleteExerciseUseCase,
    val addExercise: AddExerciseUseCase,
    val getWorkout: GetWorkoutUseCase
)
