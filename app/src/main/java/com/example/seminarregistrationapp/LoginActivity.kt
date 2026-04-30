package com.example.seminarregistrationapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seminarregistrationapp.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // =============================================
    // Data akun yang di-hardcode
    // Format: "username" to "password"
    // Bisa ditambah akun lain di sini
    // =============================================
    private val akunTerdaftar = mapOf(
        "admin"  to "admin123",
        "user1"  to "pass123",
        "budi"   to "budi2024"
    )

    // Menyimpan username yang berhasil register
    // dari RegisterActivity
    companion object {
        val akunBaru = mutableMapOf<String, String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTombolLogin()
        setupTombolKeDaftar()
    }

    // =============================================
    // Logika tombol Login
    // =============================================
    private fun setupTombolLogin() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi field tidak boleh kosong
            if (username.isEmpty()) {
                binding.tilUsername.error = "Username tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.tilUsername.error = null
                binding.tilUsername.isErrorEnabled = false
            }

            if (password.isEmpty()) {
                binding.tilPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.tilPassword.error = null
                binding.tilPassword.isErrorEnabled = false
            }

            // Gabungkan akun hardcode + akun yang baru register
            val semuaAkun = akunTerdaftar + akunBaru

            // Cek apakah username & password cocok
            if (semuaAkun[username] == password) {
                // Login berhasil — pindah ke HomeActivity
                // Kirim username agar bisa ditampilkan di Home
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("USERNAME", username)
                    // Hapus LoginActivity dari back stack
                    // agar tombol Back tidak kembali ke Login
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            } else {
                // Login gagal — tampilkan pesan error
                Snackbar.make(
                    binding.root,
                    "Username atau password salah!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Navigasi ke halaman Register
    private fun setupTombolKeDaftar() {
        binding.tvDaftarDisini.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}