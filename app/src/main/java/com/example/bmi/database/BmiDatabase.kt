package com.example.bmi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bmi.bmi.BmiData

@Database(entities = [BmiData::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun bmiDao(): BmiDao

    companion object {
        @Volatile
        private var INSTANCE: BmiDatabase? = null
        fun getDatabase(context: Context): BmiDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BmiDatabase::class.java,
                    "bmi_data"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}