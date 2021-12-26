package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout

import androidx.compose.ui.focus.FocusState

sealed class AddEditWorkoutEvent {
    data class EnteredTitle(val title: String): AddEditWorkoutEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditWorkoutEvent()
    object SaveWorkout: AddEditWorkoutEvent()
}
