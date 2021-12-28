package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise

import androidx.compose.ui.focus.FocusState

sealed class AddEditExerciseEvent {
    data class EnteredTitle(val title:String):AddEditExerciseEvent()
    data class ChangeTitleFocus(val focusState: FocusState):AddEditExerciseEvent()
    data class EnteredDuration(val Duration:Int):AddEditExerciseEvent()
    data class ChangeDurationFocus(val focusState: FocusState):AddEditExerciseEvent()
    object SaveExercise: AddEditExerciseEvent()
}