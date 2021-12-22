package com.tuwaiq.workoutassistantapplication.composescreens.screens



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout

@Composable
fun WorkoutListScreen(navController: NavController) {

        LazyColumn(){
                itemsIndexed(
                        listOf<Workout>(
                                Workout(0,"test1"),
                                Workout(1,"test2",)
                        )
                ){_, workout ->
                        Card(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                        ) {
                                Text(text = workout.workoutName)
                        }
                }


        }

}

@Preview(showBackground = true)
@Composable
fun prev() {

        val navController = rememberNavController()

        WorkoutListScreen(navController = navController)

}