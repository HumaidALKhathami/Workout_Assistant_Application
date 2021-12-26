package com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository

class GetWorkoutUseCase(
    private val repository: WorkoutRepository
) {

    suspend operator fun invoke(id: Int): Workout{
        return repository.getWorkout(id)
    }
}