package com.example.bmi.exceptions

import java.lang.Exception

class IncorrectImperialMassException : IncorrectMassException() {
    override val message: String?
        get() = "The mass must be between 60lb and 1300lb"
}