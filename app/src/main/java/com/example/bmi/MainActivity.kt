package com.example.bmi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.bmi.bmi.Bmi
import com.example.bmi.bmi.BmiImperial
import com.example.bmi.bmi.BmiMetric
import com.example.bmi.bmi_history.BmiHistory
import com.example.bmi.database.BmiDao
import com.example.bmi.database.BmiDatabase
import com.example.bmi.databinding.ActivityMainBinding
import com.example.bmi.exceptions.IncorrectHeightException
import com.example.bmi.exceptions.IncorrectMassException

/**
 * Testing device: POCO F2 PRO
 */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var lastBmi: Bmi? = null
    var isImperial: Boolean = false
    private lateinit var history: BmiHistory;
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "BMI_HISTORY"
    private lateinit var dao: BmiDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = BmiDatabase.getDatabase(application).bmiDao()
        val x = dao.getAllBmiHistory()
        history = BmiHistory(x)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelable("LAST_BMI", lastBmi)
            putBoolean("IS_IMPERIAL", isImperial)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastBmi = savedInstanceState.getParcelable<Bmi>("LAST_BMI")
        isImperial = savedInstanceState.getBoolean("IS_IMPERIAL")
        binding.apply {
            if (isImperial) {
                setImperial()
            }
            if (lastBmi == null) {
                bmiTV.text = getString(R.string.empty_value)
            } else {
                setBmiTv(bmiTV)
            }
        }
    }

    fun count(view: View) {
        binding.apply {
            if (checkBlank(massET, heightET) && checkNewBmi(massET, heightET)) {
                setBmiTv(bmiTV)
            }
        }
    }

    fun setBmiTv(bmiTV: TextView) {
        bmiTV.text = ("%.2f".format(lastBmi?.bmi))
        bmiTV.setTextColor(
            getColor(
                resources.getIdentifier(
                    "category_color_" + lastBmi?.getBmiCategory(), "color", packageName
                )
            )
        )
    }

    fun checkBlank(mass: EditText, height: EditText): Boolean {
        var notBlank: Boolean = true
        if (mass.text.isBlank()) {
            mass.error = getString(R.string.mass_is_empty)
            notBlank = false
        }
        if (height.text.isBlank()) {
            height.error = getString(R.string.height_is_empty)
            notBlank = false
        }
        return notBlank
    }

    fun checkNewBmi(mass: EditText, height: EditText): Boolean {
        try {
            val newBmiMeasurement: Bmi = if (isImperial) BmiImperial(
                mass.text.toString().toFloat(),
                height.text.toString().toFloat()
            ) else BmiMetric(
                mass.text.toString().toFloat(),
                height.text.toString().toFloat()
            )
            newBmiMeasurement.checkParams()
            lastBmi = newBmiMeasurement
            updateHistory()
        } catch (e: IncorrectMassException) {
            mass.error = e.message
            return false
        } catch (e: IncorrectHeightException) {
            height.error = e.message
            return false
        }
        return true
    }

    private fun updateHistory() {
        history.addBmi(lastBmi!!)
        dao.insertBmi(lastBmi!!.toBmiData())
        if (dao.getBmiHistoryCount()>10){
            dao.deleteBmi(dao.getTheOldestBmi())
        }
    }

    fun setMetric() {
        isImperial = false
        binding.apply {
            massTV.text = getString(R.string.mass_kg)
            heightTV.text = getString(R.string.height_cm)
        }
    }

    fun setImperial() {
        isImperial = true
        binding.apply {
            massTV.text = getString(R.string.mass_lb)
            heightTV.text = getString(R.string.height_in)
        }
    }

    fun showBmiDetails(view: View) {
        if (lastBmi == null) return

        val detailsIntent = Intent(applicationContext, BmiDetailsActivity::class.java)
        val detailsBundle = Bundle()
        detailsBundle.run {
            putParcelable("BMI", lastBmi)
            putBoolean("IS_IMPERIAL", isImperial)
        }
        detailsIntent.putExtra("DETAILS_BUNDLE", detailsBundle)
        startActivityForResult(detailsIntent, 1)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.metric -> {
                setMetric()
                true
            }
            R.id.imperial -> {
                setImperial()
                true
            }
            R.id.history -> {
                showHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showHistory() {
        val historyArray = history.getBmiHistory()
        if (historyArray.isEmpty()) {
            Toast.makeText(
                applicationContext,
                getString(R.string.empty_history),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val detailsIntent = Intent(applicationContext, BmiHistoryActivity::class.java)
        val detailsBundle = Bundle()
        detailsBundle.run {
            putParcelableArray("BMI_HISTORY", history.getBmiHistory())
        }
        detailsIntent.putExtra("DETAILS_BUNDLE", detailsBundle)
        startActivityForResult(detailsIntent, 2)
    }
}