package com.tuwaiq.workoutassistantapplication.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuwaiq.workoutassistantapplication.core.data.WorkoutExerciseDao
import com.tuwaiq.workoutassistantapplication.core.data.type_converters.ListOfIntTypeConverter
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.PersonalProfile
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout


@Database(entities = [PersonalProfile::class, Workout::class , Exercise::class] , version = 1)
@TypeConverters(ListOfIntTypeConverter::class)
abstract class WorkoutExerciseProfileDatabase : RoomDatabase() {

    abstract fun WorkoutExerciseDao() : WorkoutExerciseDao

    companion object{
        const val DATABASE_NAME = "room_db"
    }
}