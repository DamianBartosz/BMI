package com.example.bmi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bmi.bmi.BmiData

@Dao
interface BmiDao {
    @Query("SELECT * FROM BmiData")
    fun getAllBmiHistory(): List<BmiData>

    @Query("SELECT * FROM BmiData WHERE id = (SELECT MIN(id) FROM BmiData)")
    fun getTheOldestBmi(): BmiData

    @Query("SELECT COUNT(*) FROM BmiData")
    fun getBmiHistoryCount(): Int

    @Insert
    fun insertBmi(bmiData: BmiData)

    @Delete
    fun deleteBmi(bmiData: BmiData)
}