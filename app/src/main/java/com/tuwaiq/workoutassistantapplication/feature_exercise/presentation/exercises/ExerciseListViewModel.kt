package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise
import com.tuwaiq.workoutassistantapplication.feature_exercise.domain.use_case.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exerciseState = mutableStateOf(ExercisesState())
    val exerciseState: State<ExercisesState> = _exerciseState

    private var lastDeletedExercise: Exercise? = null



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
            _exerciseState.value = exerciseState.value.copy(
                exerciseUseCases.getWorkout(id)!!.workoutName,
                exerciseUseCases.getWorkout(id)!!.exercises
            )
        }
    }
}