package com.tuwaiq.workoutassistantapplication.composescreens.screens



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tuwaiq.workoutassistantapplication.composescreens.moduleandnav.Screen
import com.tuwaiq.workoutassistantapplication.roomdatabase.models.Workouts

@Composable
fun WorkoutListScreen(navController: NavController) {

        LazyColumn(){
                itemsIndexed(
                        listOf<Workouts>(
                                Workouts(0,"test1"),
                                Workouts(1,"test2",)
                        )
                ){_, workout ->
                        Card(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                        ) {
                                Text(text = workout.workoutsName)
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