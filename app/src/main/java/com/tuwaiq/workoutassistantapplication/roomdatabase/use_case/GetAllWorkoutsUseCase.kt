package com.tuwaiq.workoutassistantapplication.roomdatabase.use_case

import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts
import com.tuwaiq.workoutassistantapplication.roomdatabase.repo.Repository
import com.tuwaiq.workoutassistantapplication.roomdatabase.utils.SortingBy
import com.tuwaiq.workoutassistantapplication.roomdatabase.utils.SortingType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllWorkoutsUseCase(
    private val repository: Repository
) {

    operator fun invoke(
        sortingBy: SortingBy = SortingBy.Date(SortingType.Descending)
    ): Flow<List<Workouts>>{
        return repository.getAllWorkouts().map {workouts ->
            when (sortingBy.sortingType){
                is SortingType.Ascending -> {
                        when(sortingBy){
                            is SortingBy.Title -> workouts.sortedBy { it.workoutsName.lowercase() }
                            is SortingBy.Date -> workouts.sortedBy { it.workoutID }
                        }
                }
                is SortingType.Descending -> {
                    when(sortingBy){
                        is SortingBy.Title -> workouts.sortedByDescending { it.workoutsName.lowercase() }
                        is SortingBy.Date -> workouts.sortedByDescending { it.workoutID }
                    }
                }
            }
        }
    }
}