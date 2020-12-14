package com.example.bmi.exceptions

import java.lang.Exception

class IncorrectImperialHeightException : IncorrectHeightException() {
    override val message: String?
        get() = "The height must be between 20in and 120in"
}