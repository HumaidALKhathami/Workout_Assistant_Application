package com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository


import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {

    fun getAllWorkouts(): Flow<List<Workout>>

    suspend fun getWorkout(id : Int): Workout

    suspend fun addWorkout(workout: Workout)

    suspend fun deleteWorkout(workout: Workout)


}