package com.tuwaiq.workoutassistantapplication.roomdatabase.utils

sealed class SortingBy(val sortingType: SortingType) {
    class Title(sortingType: SortingType): SortingBy(sortingType)
    class Date(sortingType: SortingType): SortingBy(sortingType)
}