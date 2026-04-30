package com.example.seminarregistrationapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.seminarregistrationapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    // ViewBinding untuk mengakses semua view di activity_result.xml
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar tanpa tombol back
        // (user harus pakai tombol "Kembali ke Halaman Utama")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        // Ambil dan tampilkan data, lalu setup tombol
        tampilkanData()
        setupTombolKembali()
    }

    // =============================================
    // Mengambil data dari Intent yang dikirim
    // FormActivity menggunakan getStringExtra()
    // =============================================
    private fun tampilkanData() {
        // Ambil setiap data menggunakan key yang sama
        // dengan yang digunakan saat putExtra() di FormActivity
        val nama    = intent.getStringExtra("NAMA")    ?: "-"
        val email   = intent.getStringExtra("EMAIL")   ?: "-"
        val phone   = intent.getStringExtra("PHONE")   ?: "-"
        val gender  = intent.getStringExtra("GENDER")  ?: "-"
        val seminar = intent.getStringExtra("SEMINAR") ?: "-"

        // Tampilkan data ke masing-masing TextView
        binding.tvNama.text    = nama
        binding.tvEmail.text   = email
        binding.tvPhone.text   = phone
        binding.tvGender.text  = gender
        binding.tvSeminar.text = seminar
    }

    // =============================================
    // Tombol kembali ke HomeActivity
    // FLAG_ACTIVITY_CLEAR_TOP memastikan semua
    // Activity di atas Home (Form & Result) ditutup
    // =============================================
    private fun setupTombolKembali() {
        binding.btnKembali.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                // Clear semua activity di back stack sampai HomeActivity
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish() // Tutup ResultActivity
        }
    }
}