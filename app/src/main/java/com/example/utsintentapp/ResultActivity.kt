package com.example.utsintentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnHapus = findViewById<Button>(R.id.btnHapus)

        val sharedPref = getSharedPreferences("MovieData", MODE_PRIVATE)
        val size = sharedPref.getInt("size", 0)

        val stringBuilder = StringBuilder()

        for (i in 0 until size) {
            val title = sharedPref.getString("title_$i", "N/A")
            val watchFor = sharedPref.getString("watchFor_$i", "N/A")
            val genres = sharedPref.getString("genres_$i", "N/A")
            val year = sharedPref.getString("year_$i", "N/A")
            val durationHour = sharedPref.getString("durationHour_$i", "N/A")
            val durationMinutes = sharedPref.getString("durationMinutes_$i", "N/A")

            stringBuilder.append("Title: $title\nWatch for: $watchFor\nGenres: $genres\nYear: $year\nDuration: $durationHour Hours, $durationMinutes Minutes\n\n")
        }

        tvResult.text = stringBuilder.toString()

        btnBack.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        btnHapus.setOnClickListener {
            if (size > 0) {
                val editor = sharedPref.edit()
                val newSize = size - 1

                editor.remove("title_$newSize")
                editor.remove("watchFor_$newSize")
                editor.remove("genres_$newSize")
                editor.remove("year_$newSize")
                editor.remove("durationHour_$newSize")
                editor.remove("durationMinutes_$newSize")
                editor.putInt("size", newSize)
                editor.apply()

                // Refresh the ResultActivity to show the updated list
                finish()
                startActivity(intent)
            }
        }
    }
}
