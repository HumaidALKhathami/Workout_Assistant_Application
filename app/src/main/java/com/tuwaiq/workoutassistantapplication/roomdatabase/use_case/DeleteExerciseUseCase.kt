package com.tuwaiq.workoutassistantapplication.roomdatabase.use_case

import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository

class DeleteExerciseUseCase(
    private val repository: WorkoutRepository
) {
  //  suspend operator fun invoke(exercise: Exercise) = repository.deleteExercise(exercise)
}

