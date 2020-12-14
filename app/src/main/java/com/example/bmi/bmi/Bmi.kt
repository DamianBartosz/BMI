package com.example.bmi.bmi

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

abstract class Bmi(val mass: Float, val height: Float, val bmiDate: LocalDate = LocalDate.now()) :
    Parcelable {

    abstract val bmi: Float

    abstract val minCorrectMass: Float
    abstract val maxCorrectMass: Float

    abstract fun checkParams()
    abstract fun toBmiData(): BmiData;

    fun getBmiCategory(): Int {
        return when {
            bmi < 16f -> 1
            bmi < 17f -> 2
            bmi < 18.5f -> 3
            bmi < 25f -> 4
            bmi < 30f -> 5
            bmi < 35f -> 6
            bmi < 40f -> 7
            else -> 8
        }
    }

    companion object {
        fun fromBmiData(bmiData: BmiData): Bmi {
            return if (bmiData.objType == "metric") {
                BmiMetric(bmiData.mass, bmiData.height, bmiData.bmiDate)
            } else {
                BmiImperial(bmiData.mass, bmiData.height, bmiData.bmiDate)
            }
        }
    }
}


