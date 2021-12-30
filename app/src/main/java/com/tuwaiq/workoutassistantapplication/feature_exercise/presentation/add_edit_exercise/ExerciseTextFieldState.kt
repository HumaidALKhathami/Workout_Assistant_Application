package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise

data class ExerciseTextFieldState(
    val text: String ="",
    val hint: String ="",
    val isHintVisible: Boolean = true,
    var workoutId: Int = 0
)
