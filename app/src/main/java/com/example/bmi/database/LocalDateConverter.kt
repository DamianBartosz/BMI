package com.example.bmi.database

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun toString(value: LocalDate?): String? {
        return value.toString()
    }
}