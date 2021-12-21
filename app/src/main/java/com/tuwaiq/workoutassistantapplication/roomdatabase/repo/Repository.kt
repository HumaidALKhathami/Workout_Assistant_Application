package com.tuwaiq.workoutassistantapplication.roomdatabase.repo


import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllWorkouts(): Flow<List<Workouts>>

    suspend fun getWorkout(id : Int): Workouts

    suspend fun getExercise(id:Int): Exercise

    suspend fun addWorkout(workout: Workouts)

    suspend fun addExercise(exercise: Exercise)

    suspend fun deleteWorkout(workout: Workouts)

    suspend fun deleteExercise(exercise: Exercise)
}