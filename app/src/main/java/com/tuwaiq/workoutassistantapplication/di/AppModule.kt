package com.tuwaiq.workoutassistantapplication.di

import android.app.Application
import androidx.room.Room
import com.tuwaiq.workoutassistantapplication.roomdatabase.RoomDatabase
import com.tuwaiq.workoutassistantapplication.roomdatabase.repo.Repository
import com.tuwaiq.workoutassistantapplication.roomdatabase.repo.RepositoryImplementation
import com.tuwaiq.workoutassistantapplication.roomdatabase.use_case.DeleteWorkoutUseCase
import com.tuwaiq.workoutassistantapplication.roomdatabase.use_case.GetAllWorkoutsUseCase
import com.tuwaiq.workoutassistantapplication.roomdatabase.use_case.WorkoutUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app : Application): RoomDatabase{
        return Room.databaseBuilder(
            app ,
            RoomDatabase::class.java,
            RoomDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: RoomDatabase): Repository{
        return RepositoryImplementation(database.RoomDao())
    }

    @Provides
    @Singleton
    fun providesWorkoutsUseCases(repository: Repository): WorkoutUseCases{
        return WorkoutUseCases(
            getAllWorkouts = GetAllWorkoutsUseCase(repository),
            deleteWorkout = DeleteWorkoutUseCase(repository)
        )
    }
}