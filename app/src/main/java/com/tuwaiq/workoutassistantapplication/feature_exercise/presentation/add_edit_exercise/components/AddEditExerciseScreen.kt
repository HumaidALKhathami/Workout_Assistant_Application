package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise.components


import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise.AddEditExerciseEvent
import com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.add_edit_exercise.AddEditExerciseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditExerciseScreen(
    navController: NavController,
    viewModel: AddEditExerciseViewModel = hiltViewModel()
) {

    val titleState = viewModel.exerciseTitleState.value

    val durationState = viewModel.durationState.value

    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditExerciseViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditExerciseViewModel.UiEvent.SaveExercise -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton ={
            FloatingActionButton(onClick = {
                if (durationState.text.isEmpty())
                    return@FloatingActionButton

                if (titleState.text.isEmpty()){
                    val builder = AlertDialog.Builder(context)

                    builder.setTitle(context.getString(R.string.empty_title_dialog))
                    builder.setMessage(context.getString(R.string.empty_exercise_alert_message))

                    builder.setPositiveButton(context.getString(R.string.empty_title_dialog_positive_button)) { _, _ ->
                        viewModel.onEvent(AddEditExerciseEvent.SaveExercise)
                        navController.navigateUp()
                    }
                    builder.setNegativeButton(
                        context.getString(R.string.empty_title_dialog_negative_button)
                    ) { dialog, _ ->
                        dialog.cancel()
                    }
                    val alert = builder.create()
                    alert.show()
                }else {
                    viewModel.onEvent(AddEditExerciseEvent.SaveExercise)
                    navController.navigateUp()
                }
            }) {
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(R.string.save_exercise_floating_button))
            }
        }
    , scaffoldState = scaffoldState
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ){
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onTitleChange = {
                    viewModel.onEvent(AddEditExerciseEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExerciseEvent.ChangeTitleFocus(it))
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TransparentHintTextField(
                text = durationState.text,
                hint = durationState.hint,
                onTitleChange = {
                    viewModel.onEvent(AddEditExerciseEvent.EnteredDuration(it.toInt()))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExerciseEvent.ChangeDurationFocus(it))
                }
            )
        }
    }

}