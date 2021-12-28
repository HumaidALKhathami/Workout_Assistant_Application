package com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository

class GetExerciseUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(id:Int):Exercise?{
       return repository.getExercise(id)
    }
}