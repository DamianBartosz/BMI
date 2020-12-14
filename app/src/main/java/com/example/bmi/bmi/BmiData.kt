package com.example.bmi.bmi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Type
import java.time.LocalDate

@Entity
data class BmiData(
    @ColumnInfo(name = "mass") val mass: Float,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "bmiDate") val bmiDate: LocalDate,
    @ColumnInfo(name = "objType")val objType:String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}