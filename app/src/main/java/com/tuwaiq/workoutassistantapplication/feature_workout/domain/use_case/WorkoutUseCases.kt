package com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case

data class WorkoutUseCases(
    val getAllWorkouts: GetAllWorkoutsUseCase,
    val deleteWorkout: DeleteWorkoutUseCase,
    val addWorkout: AddWorkoutUseCase,
    val getWorkout: GetWorkoutUseCase
)
