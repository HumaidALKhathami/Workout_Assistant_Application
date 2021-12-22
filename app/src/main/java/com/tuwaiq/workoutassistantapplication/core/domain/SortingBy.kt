package com.tuwaiq.workoutassistantapplication.core.domain

sealed class SortingBy(val sortingType: SortingType) {
    class Title(sortingType: SortingType): SortingBy(sortingType)
    class Date(sortingType: SortingType): SortingBy(sortingType)

    fun copy(sortingType: SortingType): SortingBy {
        return when(this){
            is Title -> Title(sortingType)
            is Date -> Date(sortingType)
        }
    }
}