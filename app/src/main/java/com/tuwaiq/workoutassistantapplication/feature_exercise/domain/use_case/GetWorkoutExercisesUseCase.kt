package com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case

import android.util.Log
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

private const val TAG = "GetWorkoutExercisesUseC"

class GetWorkoutExercisesUseCase(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(workoutId :Int): Flow<List<Exercise>>{
        Log.d(TAG, "invoke: use case list ${repository.getWorkoutExercises(workoutId)}")
        return repository.getWorkoutExercises(workoutId)
    }
}