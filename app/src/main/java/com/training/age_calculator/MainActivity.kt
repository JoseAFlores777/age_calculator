package com.training.age_calculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateSelected: Calendar? = null
    private var txtDateSelected: TextView? = null
    private var txtTotalMinutes: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.txtDateSelected = findViewById<TextView>(R.id.txtDateSelected);
        this.txtTotalMinutes = findViewById<TextView>(R.id.txtTotalMinutes)


        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }



    }

    private fun clickDatePicker() {

        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select a date")
        val picker = builder.build()

        picker.show(supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it as Long
            setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)+1)
        }
    }

    private fun setDate(year: Int, month: Int, day: Int) {
        Toast.makeText(this, "The chosen year is $year, the month is $month, and the day is $day", Toast.LENGTH_LONG).show()
        val date = Calendar.getInstance()
        date.set(year, month, day)
        dateSelected = date
        this.txtDateSelected?.text = "$day/${month+1}/$year"
        calculateMinutes();
    }

    private fun calculateMinutes() {
        val date = Calendar.getInstance()
        val minutes = (date.timeInMillis - dateSelected!!.timeInMillis) / 60000
        this.txtTotalMinutes?.text = formatNumber(minutes.toInt())
    }

    private fun formatNumber(number: Int): String {
        var numberTmp = number.toString()
        for (i in numberTmp.length  downTo 0 step 3) {

            if (i != 0 && i != numberTmp.length) {
                numberTmp = numberTmp.substring(0, i) + "," + numberTmp.substring(i)
            }
        }
        return numberTmp
    }



}