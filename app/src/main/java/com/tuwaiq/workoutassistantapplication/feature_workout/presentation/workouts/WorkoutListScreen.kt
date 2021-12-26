package com.tuwaiq.workoutassistantapplication.composescreens.screens



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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.WorkoutViewModel
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.WorkoutsEvent
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components.SortingSection
import com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components.WorkoutItem
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@Composable
fun WorkoutListScreen(
        navController: NavController,
        viewModel: WorkoutViewModel = hiltViewModel()
) {

        val state = viewModel.workoutState.value

        val scope = rememberCoroutineScope()

        val scaffoldState = rememberScaffoldState()
        
        Scaffold(
                floatingActionButton = {
                        FloatingActionButton(onClick = {  }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new workout")
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
                               Text(text = "Your workouts")
                               IconButton(onClick = {
                                       viewModel.onEvent(WorkoutsEvent.ToggleSortingSection)
                               }) {
                                       Icon(imageVector = Icons.Default.Sort, contentDescription = "Sorting" )
                               }
                       }
                       AnimatedVisibility(
                               visible = state.isSortingVisible,
                               enter = fadeIn() + slideInVertically(),
                               exit = fadeOut() + slideOutVertically()
                       ) {
                               SortingSection(
                                       modifier = Modifier
                                               .fillMaxWidth()
                                               .padding(vertical = 16.dp),
                                       sortingBy = state.sortingBy,
                                       onSortingChange = {
                                               viewModel.onEvent(WorkoutsEvent.Sorting(it))
                                       }
                               )
                       }
                       Spacer(modifier = Modifier.height(16.dp))
                       
                       LazyColumn(
                               modifier = Modifier.fillMaxSize()
                       ){
                               items(state.workouts){ workout ->
                                       WorkoutItem(
                                               workout = workout,
                                               modifier = Modifier
                                                       .fillMaxWidth()
                                                       .clickable {

                                                       },
                                               onDeleteClick = {
                                                       viewModel.onEvent(WorkoutsEvent.DeleteWorkout(workout))
                                                       scope.launch {
                                                              val result = scaffoldState.snackbarHostState.showSnackbar(
                                                                       message = "Workout deleted",
                                                                       actionLabel = "undo"
                                                               )
                                                               if (result == SnackbarResult.ActionPerformed){
                                                                       viewModel.onEvent(WorkoutsEvent.RestoreWorkout)
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



