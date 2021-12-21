package com.tuwaiq.workoutassistantapplication.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tuwaiq.workoutassistantapplication.roomdatabase.dao.RoomDao
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.PersonalProfile
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Exercise
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts


@Database(entities = [PersonalProfile::class, Workouts::class , Exercise::class] , version = 1)
@TypeConverters(ListTypeConverters::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun RoomDao() : RoomDao

    companion object{
        const val DATABASE_NAME = "room_db"
    }
}