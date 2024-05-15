package com.example.utsintentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class InputActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etNIM = findViewById<EditText>(R.id.etNIM)
        val spinnerFakultas = findViewById<Spinner>(R.id.spinnerFakultas)
        val etJurusan = findViewById<EditText>(R.id.etJurusan)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.fakultas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFakultas.adapter = adapter
        }

        btnSave.setOnClickListener {
            val nama = etNama.text.toString()
            val nim = etNIM.text.toString()
            val fakultas = spinnerFakultas.selectedItem.toString()
            val jurusan = etJurusan.text.toString()

            // Save data to SharedPreferences
            val sharedPref = getSharedPreferences("MahasiswaData", MODE_PRIVATE)
            val editor = sharedPref.edit()

            // Get the current list size
            val currentSize = sharedPref.getInt("size", 0)

            // Save new data
            editor.putString("nama_$currentSize", nama)
            editor.putString("nim_$currentSize", nim)
            editor.putString("fakultas_$currentSize", fakultas)
            editor.putString("jurusan_$currentSize", jurusan)
            editor.putInt("size", currentSize + 1)
            editor.apply()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}
