package com.tuwaiq.workoutassistantapplication.core.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutExerciseDao {

    @Query("SELECT * FROM workout")
    fun getAllWorkouts():Flow<List<Workout>>

    @Query("SELECT * FROM workout WHERE workoutID = :id")
    suspend fun getWorkout(id : Int): Workout?

    @Query("SELECT * FROM exercise WHERE exerciseId = :id")
    suspend fun getExercise(id:Int): Exercise?

    @Insert(onConflict = REPLACE)
    suspend fun addWorkout(workout: Workout)

    @Insert(onConflict = REPLACE)
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise WHERE parentWorkoutId = :workoutId")
    fun getWorkoutExercises(workoutId: Int): Flow<List<Exercise>>

}