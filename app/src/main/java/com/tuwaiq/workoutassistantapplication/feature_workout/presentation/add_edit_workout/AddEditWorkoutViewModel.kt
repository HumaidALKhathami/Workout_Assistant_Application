package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case.WorkoutUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>("workoutID")?.let { workoutId ->
            if (workoutId != -1 ){
                viewModelScope.launch {
                    workoutUseCases.getWorkout(workoutId).also { workout ->
                        currentWorkoutId = workout.workoutID
                        _workoutTitle.value = _workoutTitle.value.copy(
                            text = workout.workoutName,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    private val _workoutTitle = mutableStateOf(WorkoutTextFieldState(
        hint = "Enter a Workout Title"
    ))
    val workoutTitle: State<WorkoutTextFieldState> = _workoutTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentWorkoutId : Int = 0

    fun onEvent(event: AddEditWorkoutEvent){
        when (event){
           is AddEditWorkoutEvent.EnteredTitle -> {
                _workoutTitle.value = _workoutTitle.value.copy(
                    text = event.title
                )
            }
            is AddEditWorkoutEvent.ChangeTitleFocus -> {
                _workoutTitle.value = _workoutTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _workoutTitle.value.text.isBlank()
                )
            }
            is AddEditWorkoutEvent.SaveWorkout -> {
                viewModelScope.launch{
                    workoutUseCases.addWorkout(
                        Workout(
                            workoutID = currentWorkoutId,
                            workoutName = workoutTitle.value.text,
                            exercises = emptyList()
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveWorkout)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveWorkout : UiEvent()
    }
}