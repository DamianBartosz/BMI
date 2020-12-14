package com.example.bmi

import com.example.bmi.bmi.BmiImperial
import com.example.bmi.bmi_history.BmiHistory
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class BmiHistoryTest : ShouldSpec({
    context("BmiHistory()") {
        should("construct object with empty array") {
            val bmiHistory = BmiHistory()
            bmiHistory.toString() shouldBe "[]"
        }

        should("construct object with not empty array") {
            val bmiHistory =
                BmiHistory("[metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09]")
            bmiHistory.toString() shouldBe "[metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09]"
        }
    }

    context("BmiHistory.addBmi") {
        should("add new bmi at first position of array") {
            val bmiHistory =
                BmiHistory("[metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09]")
            bmiHistory.addBmi(BmiImperial(50f, 80.0f, LocalDate.parse("2020-11-10")))
            bmiHistory.toString() shouldBe "[imperial;50.0;80.0;2020-11-10, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, imperial;60.0;90.0;2020-11-09, metric;60.0;90.0;2020-11-09]"
        }
    }
})