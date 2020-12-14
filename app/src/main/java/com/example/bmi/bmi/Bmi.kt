package com.example.bmi.bmi

import android.os.Parcelable
import java.time.LocalDate

abstract class Bmi(val mass: Float, val height: Float, val bmiDate: LocalDate = LocalDate.now()) :
    Parcelable {

    abstract val bmi: Float

    abstract val minCorrectMass: Float
    abstract val maxCorrectMass: Float

    abstract fun checkParams()

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
        fun fromString(savedStr: String): Bmi {
            val stringArr = savedStr.split(";")
            return if (stringArr[0] == "metric") {
                BmiMetric(
                    stringArr[1].toFloat(),
                    stringArr[2].toFloat(),
                    LocalDate.parse(stringArr[3])
                )
            } else {
                BmiImperial(
                    stringArr[1].toFloat(),
                    stringArr[2].toFloat(),
                    LocalDate.parse(stringArr[3])
                )
            }
        }
    }
}


