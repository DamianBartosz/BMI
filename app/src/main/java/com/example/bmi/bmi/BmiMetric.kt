package com.example.bmi.bmi

import android.os.Parcel
import android.os.Parcelable
import com.example.bmi.exceptions.IncorrectMetricHeightException
import com.example.bmi.exceptions.IncorrectMetricMassException
import java.time.LocalDate


class BmiMetric(mass: Float, height: Float, bmiDate: LocalDate = LocalDate.now()) :
    Bmi(mass, height, bmiDate) {
    override val bmi by lazy { mass / (height * height) * 10000 }
    override val minCorrectMass by lazy { calcCorrectMass(18.5f) }
    override val maxCorrectMass by lazy { calcCorrectMass(24.99f) }

    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun checkParams() {
        if (mass < 30f || mass > 600f) {
            throw IncorrectMetricMassException()
        }
        if (height < 50 || height > 300) {
            throw IncorrectMetricHeightException()
        }
    }

    override fun toBmiData(): BmiData {
        return BmiData(mass, height, bmiDate, "metric")
    }

    private fun calcCorrectMass(correctBmi: Float): Float {
        return correctBmi * height * height / 10000
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(mass)
        parcel.writeFloat(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "metric;$mass;$height;$bmiDate"
    }

    companion object CREATOR : Parcelable.Creator<BmiMetric> {
        override fun createFromParcel(parcel: Parcel): BmiMetric {
            return BmiMetric(parcel)
        }

        override fun newArray(size: Int): Array<BmiMetric?> {
            return arrayOfNulls(size)
        }
    }


}