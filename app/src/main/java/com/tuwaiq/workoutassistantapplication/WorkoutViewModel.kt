package com.tuwaiq.workoutassistantapplication

import androidx.lifecycle.ViewModel
import com.tuwaiq.workoutassistantapplication.roomdatabase.use_case.WorkoutUseCases
import javax.inject.Inject

class WorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases
):ViewModel() {

}