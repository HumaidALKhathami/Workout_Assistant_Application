package com.tuwaiq.workoutassistantapplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tuwaiq.workoutassistantapplication.core.data.data_source.WorkoutExerciseProfileDatabase
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository
import com.tuwaiq.workoutassistantapplication.feature_workout.data.repository.WorkoutRepositoryImplementation
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case.*
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
    fun provideRoomDatabase(app : Application): WorkoutExerciseProfileDatabase {
        return Room.databaseBuilder(
            app ,
            WorkoutExerciseProfileDatabase::class.java,
            WorkoutExerciseProfileDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: WorkoutExerciseProfileDatabase): WorkoutRepository {
        return WorkoutRepositoryImplementation(database.WorkoutExerciseDao())
    }

    @Provides
    @Singleton
    fun providesWorkoutsUseCases(repository: WorkoutRepository): WorkoutUseCases {
        return WorkoutUseCases(
            getAllWorkouts = GetAllWorkoutsUseCase(repository),
            deleteWorkout = DeleteWorkoutUseCase(repository),
            getWorkout = GetWorkoutUseCase(repository),
            addWorkout = AddWorkoutUseCase(repository)
        )
    }
}