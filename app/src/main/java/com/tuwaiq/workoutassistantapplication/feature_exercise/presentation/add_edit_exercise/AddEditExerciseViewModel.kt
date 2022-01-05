package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case.ExerciseUseCases
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddEditExerciseViewMode"

@HiltViewModel
class AddEditExerciseViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    savedStateHandle: SavedStateHandle,
    application: Application,
    var exerciseSample: Exercise ,
    var workoutSample: Workout
    ): AndroidViewModel(application) {

    init {

        savedStateHandle.get<Int>("exerciseId")?.let { exerciseId ->
            if (exerciseId != -1){
                viewModelScope.launch {
                    Log.d(TAG, "exercise Id : $exerciseId")

                    exerciseUseCases.getExercise(exerciseId)?.also { exercise ->
                        delay(1L)
                        exerciseIdSample = exercise.exerciseId
                        Log.d(TAG, "exerciseID: ${exercise.exerciseId}")
                        workoutSample.exercises += exercise.exerciseId
                        _exerciseTitleState.value = _exerciseTitleState.value.copy(
                            text = exercise.exerciseName,
                            isHintVisible = false
                        )
                        _durationState.value = _durationState.value.copy(
                            text = exercise.duration.toString(),
                            isHintVisible = false
                        )
                    }
                }
            }
        }

        savedStateHandle.get<Int>("workoutId")?.let { workoutId ->
            viewModelScope.launch {
                exerciseUseCases.getWorkout(workoutId)?.also { workout ->
                    workoutSample = workout
                }
            }
        }
    }
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val _exerciseTitleState = mutableStateOf(ExerciseTextFieldState(
        hint = context.getString(R.string.exercise_title_hint)
    ))


    val exerciseTitleState : State<ExerciseTextFieldState> = _exerciseTitleState

    private val _durationState = mutableStateOf(ExerciseTextFieldState(
        hint = context.getString(R.string.duration_text_hint)
    ))
    
    val durationState : State<ExerciseTextFieldState> = _durationState

    private var exerciseIdSample = -1

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditExerciseEvent){
        when(event){

            is AddEditExerciseEvent.EnteredTitle -> {
                _exerciseTitleState.value = _exerciseTitleState.value.copy(
                    text = event.title
                )
            }
            is AddEditExerciseEvent.EnteredDuration -> {
                _durationState.value = _durationState.value.copy(
                    text = event.Duration.toString()
                )
            }
            is AddEditExerciseEvent.SaveExercise -> {
                viewModelScope.launch {
                   exerciseSample = Exercise(
                       exerciseId =  exerciseIdCheck(exerciseIdSample),
                       exerciseName = exerciseTitleState.value.text,
                       duration = durationState.value.text.toInt(),
                       parentWorkoutId = workoutSample.workoutID
                    ).also { exercise ->
                        Log.d(TAG, "onEvent: exercise : $exercise")
                        exerciseUseCases.addExercise(exercise)
                        Log.d(TAG, "onEvent: workout: $workoutSample")
                        exerciseUseCases.addWorkout(workoutSample)
                    }

                    _eventFlow.emit(UiEvent.SaveExercise)
                }.invokeOnCompletion {
                    workoutSample.exercises += exerciseSample.exerciseId
                }
            }
            is AddEditExerciseEvent.ChangeTitleFocus -> {
                _exerciseTitleState.value = _exerciseTitleState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _exerciseTitleState.value.text.isBlank()
                )
            }
            is AddEditExerciseEvent.ChangeDurationFocus -> {
                _durationState.value = _durationState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _durationState.value.text.isBlank()
                )
            }

        }
    }

    private fun exerciseIdCheck(id:Int): Int{
        return if (id != -1) id
        else 0
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message:String):UiEvent()
        object SaveExercise: UiEvent()
    }
}