package com.example.seminarregistrationapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seminarregistrationapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    // Deklarasi variabel binding untuk mengakses semua view via ViewBinding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Ambil username dari Intent Login
        // Jika tidak ada, tampilkan "User" sebagai default
        val username = intent.getStringExtra("USERNAME") ?: "User"
        binding.tvUserName.text = username

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.btnDaftarSeminar.setOnClickListener {
            // Navigasi ke FormActivity saat tombol ditekan
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}