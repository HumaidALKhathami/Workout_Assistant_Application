package com.tuwaiq.workoutassistantapplication.core.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Query("SELECT * FROM workout")
    fun getAllWorkouts():Flow<List<Workout>>

    @Query("SELECT * FROM workout WHERE workoutID = :id")
    suspend fun getWorkout(id : Int): Workout

    @Query("SELECT * FROM exercise WHERE exerciseID = :id")
    suspend fun getExercise(id:Int): Exercise

    @Insert(onConflict = REPLACE)
    suspend fun addWorkout(workout: Workout)

    @Insert(onConflict = REPLACE)
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

}