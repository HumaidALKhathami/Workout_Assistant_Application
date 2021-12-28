package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises

import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise

sealed class ExercisesEvent {

    data class DeleteExercise(val exercise: Exercise): ExercisesEvent()

    object RestoreExercise: ExercisesEvent()

}
