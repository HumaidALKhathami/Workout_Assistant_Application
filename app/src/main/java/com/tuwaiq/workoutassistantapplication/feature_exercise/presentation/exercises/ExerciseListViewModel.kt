package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case.ExerciseUseCases
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ExerciseListViewModel"

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    savedStateHandle: SavedStateHandle,
    private var lastDeletedExercise: Exercise? = null ,
    var workoutSample: Workout
) : ViewModel() {

    private val _exerciseState = mutableStateOf(ExercisesState())
    val exerciseState: State<ExercisesState> = _exerciseState

    init {
        savedStateHandle.get<Int>("workoutId")?.let { workoutId ->
            getAllExercises(workoutId)
        }
    }

    fun onEvent(event: ExercisesEvent){
        when(event){

            is ExercisesEvent.DeleteExercise -> {
                viewModelScope.launch {
                    exerciseUseCases.deleteExercise(event.exercise)
                    lastDeletedExercise = event.exercise
                }
            }
            is ExercisesEvent.RestoreExercise -> {
                viewModelScope.launch {
                    exerciseUseCases.addExercise(lastDeletedExercise ?: return@launch)
                    lastDeletedExercise = null
                }
            }
        }
    }

    private fun getAllExercises(id:Int){

        viewModelScope.launch{
            workoutSample = exerciseUseCases.getWorkout(id)!!
            Log.d(TAG, "getAllExercises: workoutSample $workoutSample")
            exerciseUseCases.getWorkoutExercises(workoutSample.workoutID).collect {exercises ->
                viewModelScope.launch {
                    Log.d(TAG, "getAllExercises: exercise list $exercises")
                    _exerciseState.value = exerciseState.value.copy(

                        exerciseUseCases.getWorkout(id)!!.workoutName,
                        exercises
                    )
                }
            }
        }
    }
}