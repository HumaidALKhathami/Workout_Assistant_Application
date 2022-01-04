package com.tuwaiq.workoutassistantapplication.di

import android.app.Application
import androidx.room.Room
import com.tuwaiq.workoutassistantapplication.core.data.MainRepository
import com.tuwaiq.workoutassistantapplication.core.data.data_source.WorkoutExerciseProfileDatabase
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.repository.ExerciseRepositoryImplementation
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case.*
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
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
    fun providesRepositories(database: WorkoutExerciseProfileDatabase): MainRepository{
        return MainRepository(
            workoutRepository = WorkoutRepositoryImplementation(database.WorkoutExerciseDao()),
            exerciseRepository = ExerciseRepositoryImplementation(database.WorkoutExerciseDao())

        )
    }

    @Provides
    @Singleton
    fun providesWorkoutObject():Workout{
        return Workout()
    }

    @Provides
    @Singleton
    fun providesExerciseObject():Exercise{
        return Exercise()
    }

//    @Provides
//    @Singleton
//    fun provideWorkoutRepository(database: WorkoutExerciseProfileDatabase): WorkoutRepository {
//        return WorkoutRepositoryImplementation(database.WorkoutExerciseDao())
//    }
//
//    @Provides
//    @Singleton
//    fun provideExerciseRepository(database: WorkoutExerciseProfileDatabase): ExerciseRepository {
//        return ExerciseRepositoryImplementation(database.WorkoutExerciseDao())
//    }

    @Provides
    @Singleton
    fun providesWorkoutsUseCases(repository: MainRepository): WorkoutUseCases {
        return WorkoutUseCases(
            getAllWorkouts = GetAllWorkoutsUseCase(repository.workoutRepository),
            deleteWorkout = DeleteWorkoutUseCase(repository.workoutRepository),
            getWorkout = GetWorkoutUseCase(repository.workoutRepository),
            addWorkout = AddWorkoutUseCase(repository.workoutRepository)
        )
    }

    @Provides
    @Singleton
    fun providesExercisesUseCases(repository: MainRepository): ExerciseUseCases{
        return ExerciseUseCases(
            getExercise = GetExerciseUseCase(repository.exerciseRepository),
            deleteExercise = DeleteExerciseUseCase(repository.exerciseRepository),
            addExercise = AddExerciseUseCase(repository.exerciseRepository),
            getWorkout = GetWorkoutUseCase(repository.workoutRepository),
            addWorkout = AddWorkoutUseCase(repository.workoutRepository),
            getWorkoutExercises = GetWorkoutExercisesUseCase(repository.exerciseRepository)
        )
    }
}