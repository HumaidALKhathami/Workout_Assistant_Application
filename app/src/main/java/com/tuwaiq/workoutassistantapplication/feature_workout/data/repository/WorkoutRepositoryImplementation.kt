package com.tuwaiq.workoutassistantapplication.feature_workout.data.repository

import com.tuwaiq.workoutassistantapplication.core.data.WorkoutExerciseDao
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class WorkoutRepositoryImplementation(
    private val dao: WorkoutExerciseDao
) : WorkoutRepository {
    override fun getAllWorkouts(): Flow<List<Workout>> = dao.getAllWorkouts()

    override suspend fun getWorkout(id: Int): Workout? = dao.getWorkout(id)

    override suspend fun addWorkout(workout: Workout) = dao.addWorkout(workout)

    override suspend fun deleteWorkout(workout: Workout) = dao.deleteWorkout(workout)


}