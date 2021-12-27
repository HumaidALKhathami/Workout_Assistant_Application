package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout




import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import com.tuwaiq.workoutassistantapplication.feature_workout.domain.use_case.WorkoutUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

private const val TAG = "AddEditWorkoutViewModel"

@HiltViewModel
class AddEditWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application)  {

    init {

        savedStateHandle.get<Int>("workoutId")?.let { workoutId ->
            if (workoutId != -1 ){
                viewModelScope.launch {
                    workoutUseCases.getWorkout(workoutId)?.also{ workout ->

                        delay(1L)
                        Log.d(TAG, "workoutName: ${workout.workoutName}")
                        _workoutTitle.value = _workoutTitle.value.copy(
                                text = workout.workoutName,
                                isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    val workoutId by lazy {
        savedStateHandle.get<Int>("workoutId")
    }



    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext


    private val _workoutTitle = mutableStateOf(WorkoutTextFieldState(
        hint = context.getString(R.string.workout_title_hint)
    ))
    val workoutTitle: State<WorkoutTextFieldState> = _workoutTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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