package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts

import com.tuwaiq.workoutassistantapplication.core.domain.SortingBy
import com.tuwaiq.workoutassistantapplication.core.domain.SortingType
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout

data class WorkoutsState(
    val workouts: List<Workout> = emptyList(),
    val sortingBy: SortingBy = SortingBy.Date(SortingType.Descending),
    val isSortingVisible: Boolean = false
)
