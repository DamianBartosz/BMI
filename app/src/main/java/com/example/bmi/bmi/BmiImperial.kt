package com.example.bmi.bmi

import android.os.Parcel
import android.os.Parcelable
import com.example.bmi.exceptions.IncorrectImperialHeightException
import com.example.bmi.exceptions.IncorrectImperialMassException
import java.time.LocalDate

class BmiImperial(mass: Float, height: Float, bmiDate: LocalDate = LocalDate.now()) :
    Bmi(mass, height, bmiDate) {
    override val bmi by lazy { mass / (height * height) * 703 }
    override val minCorrectMass by lazy { calcCorrectMass(18.5f) }
    override val maxCorrectMass by lazy { calcCorrectMass(24.99f) }

    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun checkParams() {
        if (mass < 60.0f || mass > 1300.0f) {
            throw IncorrectImperialMassException()
        }
        if (height < 20.0f || height > 120.0f) {
            throw IncorrectImperialHeightException()
        }
    }

    override fun toBmiData(): BmiData {
        return BmiData(mass, height, bmiDate, "imperial")
    }

    private fun calcCorrectMass(correctBmi: Float): Float {
        return correctBmi * height * height / 703
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(mass)
        parcel.writeFloat(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "imperial;$mass;$height;$bmiDate"
    }

    companion object CREATOR : Parcelable.Creator<BmiImperial> {
        override fun createFromParcel(parcel: Parcel): BmiImperial {
            return BmiImperial(parcel)
        }

        override fun newArray(size: Int): Array<BmiImperial?> {
            return arrayOfNulls(size)
        }
    }
}