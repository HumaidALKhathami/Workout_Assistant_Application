package com.tuwaiq.workoutassistantapplication.feature_exercise.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuwaiq.workoutassistantapplication.core.data.WorkoutExerciseDao
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.repository.ExerciseRepository
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImplementation(
    private val dao: WorkoutExerciseDao
): ExerciseRepository {
    override suspend fun getExercise(id: Int): Exercise? = dao.getExercise(id)

    override suspend fun addExercise(exercise: Exercise) = dao.addExercise(exercise)

    override suspend fun deleteExercise(exercise: Exercise) = dao.deleteExercise(exercise)

    override suspend fun getWorkout(id: Int): Workout? = dao.getWorkout(id)

    override suspend fun getWorkoutExercises(exercises: List<Int>): Flow<List<Exercise>> = dao.getWorkoutExercises(exercises)

}