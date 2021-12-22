package com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case

import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.repository.WorkoutRepository
import com.tuwaiq.workoutassistantapplication.core.domain.SortingBy
import com.tuwaiq.workoutassistantapplication.core.domain.SortingType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllWorkoutsUseCase(
    private val repository: WorkoutRepository
) {

    operator fun invoke(
        sortingBy: SortingBy = SortingBy.Date(SortingType.Descending)
    ): Flow<List<Workout>>{
        return repository.getAllWorkouts().map {workouts ->
            when (sortingBy.sortingType){
                is SortingType.Ascending -> {
                        when(sortingBy){
                            is SortingBy.Title -> workouts.sortedBy { it.workoutName.lowercase() }
                            is SortingBy.Date -> workouts.sortedBy { it.workoutID }
                        }
                }
                is SortingType.Descending -> {
                    when(sortingBy){
                        is SortingBy.Title -> workouts.sortedByDescending { it.workoutName.lowercase() }
                        is SortingBy.Date -> workouts.sortedByDescending { it.workoutID }
                    }
                }
            }
        }
    }
}