package com.example.bmi.exceptions

class IncorrectMetricMassException : IncorrectMassException() {
    override val message: String?
        get() = "The mass must be between 30kg and 600kg"
}

