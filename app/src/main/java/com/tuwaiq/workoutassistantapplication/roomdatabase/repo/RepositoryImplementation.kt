package com.tuwaiq.workoutassistantapplication.roomdatabase.repo

import com.tuwaiq.workoutassistantapplication.roomdatabase.dao.RoomDao
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts
import kotlinx.coroutines.flow.Flow

class RepositoryImplementation(
    private val Dao: RoomDao
) : Repository {
    override fun getAllWorkouts(): Flow<List<Workouts>> = Dao.getAllWorkouts()

    override suspend fun getWorkout(id: Int): Workouts = Dao.getWorkout(id)

    override suspend fun getExercise(id: Int): Exercise = Dao.getExercise(id)

    override suspend fun addWorkout(workout: Workouts) = Dao.addWorkout(workout)

    override suspend fun addExercise(exercise: Exercise) = Dao.addExercise(exercise)

    override suspend fun deleteWorkout(workout: Workouts) = Dao.deleteWorkout(workout)

    override suspend fun deleteExercise(exercise: Exercise) = Dao.deleteExercise(exercise)
}