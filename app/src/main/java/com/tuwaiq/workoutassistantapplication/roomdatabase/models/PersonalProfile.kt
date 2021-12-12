package com.tuwaiq.workoutassistantapplication.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PersonalProfile(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0 ,
    var name:String = "",
    var age:Int = 0,
    var gender:String = "",
    var height:String = "",
    var weight:String = ""
)