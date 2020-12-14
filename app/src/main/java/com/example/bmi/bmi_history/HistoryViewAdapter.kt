package com.example.bmi.bmi_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.bmi.Bmi
import com.example.bmi.bmi.BmiMetric

class HistoryViewAdapter(private var bmiHistory: Array<Bmi>) :
    RecyclerView.Adapter<HistoryViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val massTV = itemView.findViewById<TextView>(R.id.massHistoryTV)
        val heightTV = itemView.findViewById<TextView>(R.id.heightHistoryTV)
        val bmiTV = itemView.findViewById<TextView>(R.id.bmiHistoryTV)
        val dateTV = itemView.findViewById<TextView>(R.id.bmiDateHistoryTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val historyRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_row, parent, false)
        return ViewHolder(historyRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bmi: Bmi = bmiHistory[position]

        val massStr: String
        val heightStr: String
        val bmiStr: String = "Bmi: %.2f".format(bmi.bmi)
        val dateStr: String = "Date: " + bmi.bmiDate

        if (bmi is BmiMetric) {
            massStr = "Mass: %.1fkg".format(bmi.mass)
            heightStr = "Height: %.0fcm".format(bmi.height)
        } else {
            massStr = "Mass: %.1flb".format(bmi.mass)
            heightStr = "Height: %.1fin".format(bmi.height)

        }
        holder.apply {
            massTV.text = massStr
            heightTV.text = heightStr
            bmiTV.text = bmiStr
            dateTV.text = dateStr
        }
    }

    override fun getItemCount(): Int {
        return bmiHistory.size
    }

}