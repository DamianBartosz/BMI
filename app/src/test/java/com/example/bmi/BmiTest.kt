package com.example.bmi

import com.example.bmi.bmi.Bmi
import com.example.bmi.bmi.BmiImperial
import com.example.bmi.bmi.BmiMetric
import com.example.bmi.exceptions.IncorrectHeightException
import com.example.bmi.exceptions.IncorrectMassException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class BmiTest : ShouldSpec({
    context("Bmi.checkParams") {
        should("throw exceptions for incorrect metric mass param") {
            var bmi: Bmi = BmiMetric(0f, 20f)
            var exception: Exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 30kg and 600kg"

            bmi = BmiMetric(20f, 0f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 30kg and 600kg"

            bmi = BmiMetric(-1f, 245.245f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 30kg and 600kg"

            bmi = BmiMetric(800f, 54f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 30kg and 600kg"
        }

        should("throw exceptions for incorrect imperial mass param") {
            var bmi: Bmi = BmiImperial(0f, 11453f)
            var exception: Exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 60lb and 1300lb"

            bmi = BmiImperial(50.12f, -164f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 60lb and 1300lb"

            bmi = BmiImperial(-345f, 4145f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 60lb and 1300lb"

            bmi = BmiImperial(1500f, 63f)
            exception = shouldThrow<IncorrectMassException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The mass must be between 60lb and 1300lb"
        }

        should("throw exceptions for incorrect metric height param") {
            var bmi: Bmi = BmiMetric(40f, 0f)
            var exception: Exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 50cm and 300cm"

            bmi = BmiMetric(30f, -20f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 50cm and 300cm"

            bmi = BmiMetric(100f, 49f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 50cm and 300cm"

            bmi = BmiMetric(100f, 301f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 50cm and 300cm"
        }

        should("throw exceptions for incorrect imperial height param") {
            var bmi: Bmi = BmiImperial(60f, 0f)
            var exception: Exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 20in and 120in"

            bmi = BmiImperial(70f, -20f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 20in and 120in"

            bmi = BmiImperial(1000f, 19f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 20in and 120in"

            bmi = BmiImperial(1300f, 121f)
            exception = shouldThrow<IncorrectHeightException> {
                bmi.checkParams()
            }
            exception.message shouldBe "The height must be between 20in and 120in"
        }

        should("not throw exceptions for correct params") {
            var bmi: Bmi = BmiMetric(60f, 160f)
            shouldNotThrow<Exception> {
                bmi.checkParams()
            }

            bmi = BmiImperial(130f, 60f)
            shouldNotThrow<Exception> {
                bmi.checkParams()
            }
        }
    }

    context("Bmi.bmi") {
        should("caclulate correct bmi for metric units") {
            var bmi = BmiMetric(30f, 50f)
            "%.2f".format(bmi.bmi) shouldBe "120,00"

            bmi = BmiMetric(40f, 100f)
            "%.2f".format(bmi.bmi) shouldBe "40,00"

            bmi = BmiMetric(600f, 300f)
            "%.2f".format(bmi.bmi) shouldBe "66,67"
        }

        should("calculate correct bmi for imperial units") {
            var bmi = BmiImperial(60f, 20f)
            "%.2f".format(bmi.bmi) shouldBe "105,45"

            bmi = BmiImperial(250f, 50f)
            "%.2f".format(bmi.bmi) shouldBe "70,30"

            bmi = BmiImperial(1300f, 120f)
            "%.2f".format(bmi.bmi) shouldBe "63,47"
        }
    }

    context("Bmi.getBmiCategory") {
        should("return correct bmi category for metric units") {
            var bmi = BmiMetric(40f, 170f)
            bmi.getBmiCategory() shouldBe 1

            bmi = BmiMetric(50f, 175f)
            bmi.getBmiCategory() shouldBe 2

            bmi = BmiMetric(55f, 175f)
            bmi.getBmiCategory() shouldBe 3

            bmi = BmiMetric(60f, 170f)
            bmi.getBmiCategory() shouldBe 4

            bmi = BmiMetric(100f, 185f)
            bmi.getBmiCategory() shouldBe 5

            bmi = BmiMetric(112f, 185f)
            bmi.getBmiCategory() shouldBe 6

            bmi = BmiMetric(123f, 183f)
            bmi.getBmiCategory() shouldBe 7

            bmi = BmiMetric(146f, 175f)
            bmi.getBmiCategory() shouldBe 8
        }

        should("return correct bmi category for imperial units") {
            var bmi = BmiImperial(140f, 80f)
            bmi.getBmiCategory() shouldBe 1

            bmi = BmiImperial(150f, 80f)
            bmi.getBmiCategory() shouldBe 2

            bmi = BmiImperial(156f, 80f)
            bmi.getBmiCategory() shouldBe 3

            bmi = BmiImperial(160f, 70f)
            bmi.getBmiCategory() shouldBe 4

            bmi = BmiImperial(200f, 70f)
            bmi.getBmiCategory() shouldBe 5

            bmi = BmiImperial(200f, 64f)
            bmi.getBmiCategory() shouldBe 6

            bmi = BmiImperial(210f, 64f)
            bmi.getBmiCategory() shouldBe 7

            bmi = BmiImperial(230f, 59f)
            bmi.getBmiCategory() shouldBe 8
        }
    }

    context("Bmi.fromString") {
        should("return BmiMetric") {
            val bmi = Bmi.fromString("metric;80;180;2020-11-09")
            bmi.shouldBeInstanceOf<BmiMetric>()
            bmi.mass shouldBe 80f
            bmi.height shouldBe 180f
            bmi.bmiDate.toString() shouldBe "2020-11-09"
        }

        should("return BmiImperial") {
            val bmi = Bmi.fromString("imperial;156;80;2020-10-21")
            bmi.shouldBeInstanceOf<BmiImperial>()
            bmi.mass shouldBe 156f
            bmi.height shouldBe 80f
            bmi.bmiDate.toString() shouldBe "2020-10-21"
        }
    }

    context("Bmi.minCorrectMass") {
        should("return minimal correct mass in kg") {
            val bmi = BmiMetric(80f, 180f)
            "%.2f".format(bmi.minCorrectMass) shouldBe "59,94"
        }

        should("return minimal correct mass in lb") {
            val bmi = BmiImperial(200f, 80f)
            "%.2f".format(bmi.minCorrectMass) shouldBe "168,42"
        }
    }

    context("Bmi.maxCorrectMass") {
        should("return maximal correct mass in kg") {
            val bmi = BmiMetric(80f, 180f)
            "%.2f".format(bmi.maxCorrectMass) shouldBe "80,97"
        }

        should("return maximal correct mass in lb") {
            val bmi = BmiImperial(200f, 80f)
            "%.2f".format(bmi.maxCorrectMass) shouldBe "227,50"
        }
    }
})