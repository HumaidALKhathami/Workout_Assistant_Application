package com.tuwaiq.workoutassistantapplication.core.data.type_converters

import androidx.room.TypeConverter


class ListOfIntTypeConverter {


    @TypeConverter
    fun stringToListOfInt(string: String): List<Int> {

        if (string.isEmpty())
            return emptyList()

        val toStringList = string.split(",")

        val toListInt = mutableListOf<Int>()

        toStringList.forEach {
            toListInt += it.toInt()
        }

        return toListInt
    }

    @TypeConverter
    fun listOfIntToString(list: List<Int>):String = list.joinToString(",")
}