package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts



import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.core.presentation.Screen
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components.SortingSection
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components.WorkoutItem
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Composable
fun WorkoutListScreen(
        navController: NavController,
        listViewModel: WorkoutListViewModel = hiltViewModel()
) {

        val state = listViewModel.workoutState.value

        val scope = rememberCoroutineScope()

        val scaffoldState = rememberScaffoldState()

        val context = LocalContext.current
        
        Scaffold(
                floatingActionButton = {
                        FloatingActionButton(onClick = { navController.navigate(Screen.AddEditWorkoutScreen.route) }) {
                                Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = stringResource(R.string.new_workout_floating_button)
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
                       Row(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalArrangement = Arrangement.SpaceBetween,
                               verticalAlignment = Alignment.CenterVertically
                       ) {
                               Text(text = stringResource(R.string.workout_list))
                               IconButton(onClick = {
                                       listViewModel.onEvent(WorkoutsEvent.ToggleSortingSection)
                               }) {
                                       Icon(
                                               imageVector = Icons.Default.Sort,
                                               contentDescription = stringResource(
                                                       R.string.sorting_button_description
                                               )
                                       )
                               }
                       }
                       AnimatedVisibility(
                               visible = state.isSortingVisible,
                               enter = fadeIn() + slideInVertically(),
                               exit = fadeOut() + slideOutVertically()
                       ) {
                               SortingSection(modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(vertical = 16.dp),
                                       sortingBy = state.sortingBy,
                                       onSortingChange = {
                                               listViewModel.onEvent(WorkoutsEvent.Sorting(it))
                                       })
                       }

                       Spacer(modifier = Modifier.height(16.dp))
                       
                       LazyColumn(modifier = Modifier.fillMaxSize()){
                               items(state.workouts){ workout ->
                                       WorkoutItem(
                                               workout = workout,
                                               modifier = Modifier
                                                       .fillMaxWidth()
                                                       .clickable {
                                                               navController.navigate(
                                                                       Screen.ExerciseListScreen.route +
                                                                               "?workoutId=${workout.workoutID}"
                                                               )
                                                       },
                                               onDeleteClick = {
                                                       listViewModel.onEvent(
                                                               WorkoutsEvent.DeleteWorkout(workout)
                                                       )
                                                       scope.launch {
                                                               val result =
                                                                       scaffoldState.snackbarHostState.showSnackbar(
                                                                               message = context.getString(R.string.workout_deleted_snackbar),
                                                                               actionLabel = context.getString(R.string.undo_action_label)
                                                                       )
                                                               if (result == SnackbarResult.ActionPerformed) {
                                                                       listViewModel.onEvent(
                                                                               WorkoutsEvent.RestoreWorkout
                                                                       )
                                                               }
                                                       }
                                               },
                                               navController = navController
                                       )
                                       Spacer(modifier = Modifier.height(16.dp))
                               }

                       }

               }
        }
}



