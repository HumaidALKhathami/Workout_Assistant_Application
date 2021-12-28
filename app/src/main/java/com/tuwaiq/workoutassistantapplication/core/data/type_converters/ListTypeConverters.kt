package com.tuwaiq.workoutassistantapplication.core.data.type_converters

import androidx.room.TypeConverter
import com.tuwaiq.workoutassistantapplication.feature_exercise.data.data_source.Exercise


class ListTypeConverters {


        @TypeConverter
        fun toListOfExercise(flatStringList: String): List<Exercise> {

                if (!flatStringList.isEmpty()) {
                        val stringList = flatStringList.split("/")

                        val exercisesList = mutableListOf<Exercise>()
                        stringList.forEach {

                                val item = it.split("=")

                                val exercise = Exercise(
                                        exerciseName = item[1].removeSuffix(", duration"),
                                        duration = item[2].removeSuffix(")").toInt()
                                )

                                exercisesList.add(exercise)
                        }


                        return exercisesList
                }else{
                        return emptyList()
                }
        }


        @TypeConverter
        fun fromListOfExercise(listOfString: List<Exercise>): String {
                return listOfString.joinToString("/")
        }

}