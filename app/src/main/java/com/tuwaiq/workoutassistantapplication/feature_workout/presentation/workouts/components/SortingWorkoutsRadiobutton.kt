package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortingWorkoutsRadioButton(
    text:String,
    selected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = selected,
            onClick = onSelected
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}