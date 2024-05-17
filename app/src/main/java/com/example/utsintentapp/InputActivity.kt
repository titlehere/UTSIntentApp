package com.example.utsintentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast

class InputActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val btnOK = findViewById<Button>(R.id.btnOK)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val rbAdult = findViewById<RadioButton>(R.id.rbAdult)
        val rbKids = findViewById<RadioButton>(R.id.rbKids)
        val cbThriller = findViewById<CheckBox>(R.id.cbThriller)
        val cbHorror = findViewById<CheckBox>(R.id.cbHorror)
        val cbAction = findViewById<CheckBox>(R.id.cbAction)
        val cbRomance = findViewById<CheckBox>(R.id.cbRomance)
        val cbComedy = findViewById<CheckBox>(R.id.cbComedy)
        val cbFantasy = findViewById<CheckBox>(R.id.cbFantasy)
        val etYear = findViewById<EditText>(R.id.etYear)
        val spinnerHour = findViewById<Spinner>(R.id.spinnerHour)
        val spinnerMinutes = findViewById<Spinner>(R.id.spinnerMinutes)

        //Spinner hours
        ArrayAdapter.createFromResource(
            this,
            R.array.hours_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerHour.adapter = adapter
        }

        // Spinner minutes
        ArrayAdapter.createFromResource(
            this,
            R.array.minutes_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMinutes.adapter = adapter
        }


        btnOK.setOnClickListener {
            val title = etTitle.text.toString()
            val watchFor = if (rbAdult.isChecked) "Adult" else if (rbKids.isChecked) "Kids" else ""
            val genres = mutableListOf<String>()
            if (cbThriller.isChecked) genres.add("Thriller")
            if (cbHorror.isChecked) genres.add("Horror")
            if (cbAction.isChecked) genres.add("Action")
            if (cbRomance.isChecked) genres.add("Romance")
            if (cbComedy.isChecked) genres.add("Comedy")
            if (cbFantasy.isChecked) genres.add("Fantasy")
            val year = etYear.text.toString()
            val durationHour = spinnerHour.selectedItem.toString()
            val durationMinutes = spinnerMinutes.selectedItem.toString()

            val sharedPref = getSharedPreferences("MovieData", MODE_PRIVATE)
            val editor = sharedPref.edit()
            val size = sharedPref.getInt("size", 0)

            editor.putString("title_$size", title)
            editor.putString("watchFor_$size", watchFor)
            editor.putString("genres_$size", genres.joinToString(", "))
            editor.putString("year_$size", year)
            editor.putString("durationHour_$size", durationHour)
            editor.putString("durationMinutes_$size", durationMinutes)
            editor.putInt("size", size + 1)
            editor.apply()

            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}
