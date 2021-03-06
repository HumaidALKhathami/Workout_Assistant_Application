package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.core.presentation.Screen
import com.tuwaiq.workoutassistantapplication.feature_workout.data.data_source.Workout

@Composable
fun WorkoutItem(
    workout: Workout,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit,
    navController: NavController
    ) {
    
    Box(modifier = modifier){
        
        Canvas(modifier = Modifier.matchParentSize()){

            val clipPath = Path().apply {
                lineTo(size.width , 0f)
                lineTo(size.width , size.height)
                lineTo(size.width , size.height)
                lineTo(0f , size.height)
                close()
            }

            clipPath(clipPath){
                drawRoundRect(
                    color = Color.Cyan,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ){
            Text(text = workout.workoutName)
            Spacer(modifier = Modifier.height(8.dp))
            
        }
        Row(modifier = Modifier.align(Alignment.BottomEnd)){

            IconButton(
                onClick = {
                    navController.navigate(
                        Screen.AddEditWorkoutScreen.route +
                                "?workoutId=${workout.workoutID}"
                    )
                }

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_workout)
                )
            }

            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete_workout)
                )
            }

        }
    }
}