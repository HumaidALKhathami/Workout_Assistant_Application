package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts

import com.tuwaiq.workoutassistantapplication.core.domain.SortingBy
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout

sealed class WorkoutsEvent{

    data class Sorting(val sortingBy: SortingBy): WorkoutsEvent()

    data class DeleteWorkout(val workout: Workout): WorkoutsEvent()

    object RestoreWorkout:WorkoutsEvent()

    object ToggleSortingSection: WorkoutsEvent()

}
