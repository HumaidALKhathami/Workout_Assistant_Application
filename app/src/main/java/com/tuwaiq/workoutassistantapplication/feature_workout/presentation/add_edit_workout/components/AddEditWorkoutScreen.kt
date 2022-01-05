package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout.components

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout.AddEditWorkoutEvent
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.add_edit_workout.AddEditWorkoutViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditWorkoutScreen(
    navController: NavController,
    viewModel: AddEditWorkoutViewModel = hiltViewModel()
) {

    val titleState = viewModel.workoutTitle.value

    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest {event ->
            when (event){
                is AddEditWorkoutViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditWorkoutViewModel.UiEvent.SaveWorkout -> {
                    navController.popBackStack()
                }
            }
        }
    }
    
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {

                if (titleState.text.isEmpty()) {
                    val builder = AlertDialog.Builder(context)

                    builder.setTitle(context.getString(R.string.empty_title_dialog))
                    builder.setMessage(context.getString(R.string.empty_title_dialog_message))

                    builder.setPositiveButton(context.getString(R.string.empty_title_dialog_positive_button)) { _, _ ->
                        viewModel.onEvent(AddEditWorkoutEvent.SaveWorkout)
                    }
                    builder.setNegativeButton(
                        context.getString(R.string.empty_title_dialog_negative_button)
                    ) { dialog, _ ->
                        dialog.cancel()
                    }
                    val alert = builder.create()
                    alert.show()
                }else {
                    viewModel.onEvent(AddEditWorkoutEvent.SaveWorkout)
                }
            }
            ){
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(R.string.save_workout_floating_button))
            }
        },
        scaffoldState = scaffoldState
    ) {
        TransparentHintTextField(
            text = titleState.text,
            hint = titleState.hint,
            onTitleChange = {
                viewModel.onEvent(AddEditWorkoutEvent.EnteredTitle(it))
            },
            onFocusChange = {
                viewModel.onEvent(AddEditWorkoutEvent.ChangeTitleFocus(it))
            },
            isHintVisible = titleState.isHintVisible
        )
    }
}