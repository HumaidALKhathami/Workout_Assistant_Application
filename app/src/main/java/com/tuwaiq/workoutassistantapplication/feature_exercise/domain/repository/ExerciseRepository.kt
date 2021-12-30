package com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository

import androidx.lifecycle.LiveData
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun getExercise(id:Int): Exercise?

    suspend fun addExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

    suspend fun getWorkout(id : Int): Workout?

    fun getWorkoutExercises(workoutId: Int): Flow<List<Exercise>>
}