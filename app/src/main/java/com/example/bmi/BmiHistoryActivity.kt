package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.bmi.Bmi
import com.example.bmi.bmi_history.HistoryViewAdapter

class BmiHistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var history:Array<Bmi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_history)
        val historyParc = intent.getBundleExtra("DETAILS_BUNDLE")?.getParcelableArray("BMI_HISTORY")!!
        history = parcToBmi(historyParc)
        val viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.historyView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = HistoryViewAdapter(history)
        }
    }

    private fun parcToBmi(historyParc:Array<Parcelable>):Array<Bmi>{
        val bmiList = ArrayList<Bmi>()
        for (item in historyParc){
            bmiList.add(item as Bmi)
        }
        return bmiList.toTypedArray()
    }

}