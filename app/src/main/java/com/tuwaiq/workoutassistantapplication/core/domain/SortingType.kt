package com.tuwaiq.workoutassistantapplication.core.domain

sealed class SortingType{
    object Ascending: SortingType()
    object Descending: SortingType()
}
