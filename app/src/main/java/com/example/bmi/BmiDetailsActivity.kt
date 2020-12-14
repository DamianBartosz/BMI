package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmi.bmi.Bmi
import com.example.bmi.databinding.ActivityBmiDetailsBinding

class BmiDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityBmiDetailsBinding

    var massUnit: String = "[kg]"
    var heightUnit: String = "[cm]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiDetailsBinding.inflate(layoutInflater)
        val detailsBundle: Bundle? = intent.getBundleExtra("DETAILS_BUNDLE")
        val bmi: Bmi? = detailsBundle?.getParcelable<Bmi>("BMI")
        val isImperial: Boolean? = detailsBundle?.getBoolean("IS_IMPERIAL")

        if (bmi == null || isImperial == null) return
        if (isImperial) {
            massUnit = "[lb]"
            heightUnit = "[in]"
        }
        binding.apply {
            massDetailsTV.text = getString(R.string.mass_details, massUnit, "%.1f".format(bmi.mass))
            heightDetailsTV.text = getString(R.string.height_details, heightUnit, "%.1f".format(bmi.height))
            bmiDetailsTV.text = getString(R.string.bmi_details, "%.2f".format(bmi.bmi))
            bmiVisualisation.setImageResource(
                resources.getIdentifier(
                    "category_image_" + bmi.getBmiCategory(), "drawable", packageName
                )
            )
            bmiCategoryTV.text = getString(
                resources.getIdentifier(
                    "category_name_" + bmi.getBmiCategory(), "string", packageName
                )
            )
            bmiCategoryTV.setTextColor(
                getColor(
                    resources.getIdentifier(
                        "category_color_" + bmi.getBmiCategory(), "color", packageName
                    )
                )
            )
            minMass.text = getString(R.string.min_mass, massUnit, "%.1f".format(bmi.minCorrectMass))
            maxMass.text = getString(R.string.max_mass, massUnit, "%.1f".format(bmi.maxCorrectMass))
        }
        setContentView(binding.root)
    }
}