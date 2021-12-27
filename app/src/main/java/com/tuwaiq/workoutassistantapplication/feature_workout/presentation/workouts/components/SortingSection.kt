package com.tuwaiq.workoutassistantapplication.feature_workout.presentation.workouts.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tuwaiq.workoutassistantapplication.R
import com.tuwaiq.workoutassistantapplication.core.domain.SortingBy
import com.tuwaiq.workoutassistantapplication.core.domain.SortingType


@Composable
fun SortingSection(
    modifier: Modifier = Modifier,
    sortingBy: SortingBy = SortingBy.Date(SortingType.Descending),
    onSortingChange: (SortingBy) -> Unit
) {
    Column (
        modifier = modifier
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SortingWorkoutsRadioButton(
                text = stringResource(R.string.sorting_by_title),
                selected = sortingBy is SortingBy.Title,
                onSelected = { onSortingChange(SortingBy.Title(sortingBy.sortingType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            SortingWorkoutsRadioButton(
                text = stringResource(R.string.sorting_by_date),
                selected = sortingBy is SortingBy.Date,
                onSelected = { onSortingChange(SortingBy.Date(sortingBy.sortingType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SortingWorkoutsRadioButton(
                text = stringResource(R.string.sorting_typ_ascending),
                selected = sortingBy.sortingType is SortingType.Ascending,
                onSelected = {
                    onSortingChange(sortingBy.copy(SortingType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))

            SortingWorkoutsRadioButton(
                text = stringResource(R.string.sorting_type_descending),
                selected = sortingBy.sortingType is SortingType.Descending,
                onSelected = {
                    onSortingChange(sortingBy.copy(SortingType.Descending))
                }
            )
        }
    }
}