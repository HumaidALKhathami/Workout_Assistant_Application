package com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository

class GetWorkoutUseCase(
    private val repository: WorkoutRepository
) {

    suspend operator fun invoke(id: Int){
        repository.getWorkout(id)
    }
}