package com.tuwaiq.workoutassistantapplication.roomdatabase.use_case

import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts
import com.tuwaiq.workoutassistantapplication.roomdatabase.repo.Repository

class DeleteWorkoutUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(workouts: Workouts) = repository.deleteWorkout(workouts)
}