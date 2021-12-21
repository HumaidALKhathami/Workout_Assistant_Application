package com.tuwaiq.workoutassistantapplication.roomdatabase.use_case

import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.roomdatabase.repo.Repository

class DeleteExerciseUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(exercise: Exercise) = repository.deleteExercise(exercise)
}