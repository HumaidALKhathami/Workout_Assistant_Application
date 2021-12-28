package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.core.domain.SortingBy
import com.tuwaiq.workoutassistantapplication.core.domain.SortingType
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case.WorkoutUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases
):ViewModel() {

    private val _workoutState = mutableStateOf(WorkoutsState())

    val workoutState: State<WorkoutsState> = _workoutState

    private var lastDeletedWorkout : Workout? = null

    private var getWorkoutsJob: Job? = null

    init {
        getAllWorkouts(SortingBy.Date(SortingType.Descending))
    }

    fun onEvent(event: WorkoutsEvent){

        when(event){

            is WorkoutsEvent.Sorting ->{
                if (workoutState.value.sortingBy::class == event.sortingBy::class &&
                    workoutState.value.sortingBy.sortingType == event.sortingBy.sortingType
                ){
                    return
                }
                    getAllWorkouts(event.sortingBy)
            }
            is WorkoutsEvent.DeleteWorkout -> {
                viewModelScope.launch {
                    workoutUseCases.deleteWorkout(event.workout)
                    lastDeletedWorkout = event.workout
                }
            }
            is WorkoutsEvent.RestoreWorkout -> {
                viewModelScope.launch {
                    workoutUseCases.addWorkout(lastDeletedWorkout ?: return@launch)
                    lastDeletedWorkout = null
                }
            }
            is WorkoutsEvent.ToggleSortingSection -> {
                _workoutState.value = workoutState.value.copy(
                    isSortingVisible = !workoutState.value.isSortingVisible
                )
            }
        }
    }

    private fun getAllWorkouts(sortingBy : SortingBy){
        getWorkoutsJob?.cancel()

        getWorkoutsJob = workoutUseCases.getAllWorkouts(sortingBy)
            .onEach { workouts ->
                _workoutState.value = workoutState.value.copy(
                    workouts = workouts,
                    sortingBy = sortingBy
                )
            }
            .launchIn(viewModelScope)
    }
}