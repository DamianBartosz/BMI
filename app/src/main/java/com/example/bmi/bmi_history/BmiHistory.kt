package com.example.bmi.bmi_history

import com.example.bmi.bmi.Bmi

class BmiHistory(savedStr: String = "") {
    private val historyArray = Array<Bmi?>(10) { null }
    private var index: Int = 0

    init {
        if (savedStr.isNotEmpty()) {
            val stringTrimmed = savedStr.trim('[', ']')
            val stringArr = stringTrimmed.split(", ")
            for (item in stringArr.reversed()) {
                addBmi(Bmi.fromString(item))
            }
        }
    }

    fun addBmi(bmi: Bmi) {
        historyArray[index] = bmi
        index = (index + 1) % 10
    }

    fun getBmiHistory(): Array<Bmi> {
        val resultList = ArrayList<Bmi>()
        var copyIndex: Int = (index + 9) % 10
        for (i in 0..9) {
            if (historyArray[copyIndex] == null) return resultList.toTypedArray()
            resultList.add(historyArray[copyIndex]!!)
            copyIndex = (copyIndex + 9) % 10
        }
        return resultList.toTypedArray()
    }

    override fun toString(): String {
        return getBmiHistory().contentToString()
    }
}

