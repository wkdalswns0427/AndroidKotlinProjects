package com.example.bmi_prt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class ResultActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        val bmi = weight / (height / 100).toDouble().pow(2.0) //val bmi = weight / Math.pow((height/100).toDouble(),2.0)
        val resultText = when{
            bmi >= 35.0 -> "P"
            bmi >= 25.0 -> "N"
            bmi >= 15.0 -> "D"
            else -> "B"
        }

        val resultValueTextView = findViewById<TextView>(R.id.resultW)
        val resultStringTextView = findViewById<TextView>(R.id.resultText)

        resultValueTextView.text = bmi.toString()
        resultStringTextView.text = resultText
    }
}