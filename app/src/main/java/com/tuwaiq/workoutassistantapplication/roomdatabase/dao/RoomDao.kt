package com.tuwaiq.workoutassistantapplication.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts():Flow<List<Workouts>>

    @Query("SELECT * FROM workouts WHERE workoutID = :id")
    suspend fun getWorkout(id : Int): Workouts

    @Query("SELECT * FROM exercise WHERE exerciseID = :id")
    suspend fun getExercise(id:Int): Exercise

    @Insert(onConflict = REPLACE)
    suspend fun addWorkout(workout: Workouts)

    @Insert(onConflict = REPLACE)
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteWorkout(workout: Workouts)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

}