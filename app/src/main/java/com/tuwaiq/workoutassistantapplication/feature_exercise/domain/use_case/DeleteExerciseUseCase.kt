package com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository

class DeleteExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise){
        repository.deleteExercise(exercise)
    }
}