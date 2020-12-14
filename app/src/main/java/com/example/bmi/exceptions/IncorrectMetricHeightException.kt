package com.example.bmi.exceptions

import java.lang.Exception

class IncorrectMetricHeightException : IncorrectHeightException() {
    override val message: String?
        get() = "The height must be between 50cm and 300cm"
}