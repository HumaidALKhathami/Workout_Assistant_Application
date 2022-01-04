package com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.core.presentation.Screen
import com.tuwaiq.workoutassistantapplication.feature_exercise.presentation.exercises.components.ExerciseItem
import kotlinx.coroutines.launch

@Composable
fun ExerciseListScreen(
    navController: NavController,
    listViewModel: ExerciseListViewModel = hiltViewModel()
) {

    val state = listViewModel.exerciseState.value

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    Scaffold(
        floatingActionButton ={
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditExerciseScreen.route + "?workoutId=${listViewModel.workoutSample.workoutID}")
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.new_exercise_floating_button)
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = state.workoutName, modifier = Modifier.padding(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.exercises) { exercise ->
                    ExerciseItem(exercise = exercise,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.AddEditExerciseScreen.route + "?exerciseId=${exercise.exerciseId}&workoutId=${listViewModel.workoutSample.workoutID}")
                            },
                        onDeleteClick = {
                            listViewModel.onEvent(
                                ExercisesEvent.DeleteExercise(exercise)
                            )
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "exercise deleted",
                                    actionLabel = context.getString(R.string.undo_action_label)
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    listViewModel.onEvent(ExercisesEvent.RestoreExercise)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}