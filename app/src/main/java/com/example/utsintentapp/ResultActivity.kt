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

        val sharedPref = getSharedPreferences("MahasiswaData", MODE_PRIVATE)
        val size = sharedPref.getInt("size", 0)

        val stringBuilder = StringBuilder()

        for (i in 0 until size) {
            val nama = sharedPref.getString("nama_$i", "N/A")
            val nim = sharedPref.getString("nim_$i", "N/A")
            val fakultas = sharedPref.getString("fakultas_$i", "N/A")
            val jurusan = sharedPref.getString("jurusan_$i", "N/A")

            stringBuilder.append("Nama: $nama\nNIM: $nim\nFakultas: $fakultas\nJurusan: $jurusan\n\n")
        }

        tvResult.text = stringBuilder.toString()

        btnBack.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }
}
