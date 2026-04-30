package com.example.seminarregistrationapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seminarregistrationapp.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTombolRegister()
        setupTombolKeLogin()
    }

    // =============================================
    // Logika tombol Register
    // =============================================
    private fun setupTombolRegister() {
        binding.btnRegister.setOnClickListener {

            val nama     = binding.etNamaRegister.text.toString().trim()
            val username = binding.etUsernameRegister.text.toString().trim()
            val password = binding.etPasswordRegister.text.toString().trim()
            val konfirmasi = binding.etKonfirmasiPassword.text.toString().trim()

            // Jalankan validasi — jika gagal, berhenti
            if (!validateForm(nama, username, password, konfirmasi)) return@setOnClickListener

            // Cek apakah username sudah dipakai
            val semuaAkun = mapOf(
                "admin" to "admin123",
                "user1" to "pass123",
                "budi"  to "budi2024"
            ) + LoginActivity.akunBaru

            if (semuaAkun.containsKey(username)) {
                binding.tilUsernameRegister.error = "Username sudah digunakan"
                return@setOnClickListener
            }

            // Simpan akun baru ke companion object di LoginActivity
            LoginActivity.akunBaru[username] = password

            // Tampilkan pesan sukses
            Snackbar.make(
                binding.root,
                "Registrasi berhasil! Silakan login.",
                Snackbar.LENGTH_SHORT
            ).show()

            // Kembali ke LoginActivity setelah 1 detik
            binding.root.postDelayed({
                val intent = Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent)
                finish()
            }, 1000)
        }
    }

    // =============================================
    // Validasi semua field register
    // =============================================
    private fun validateForm(
        nama: String,
        username: String,
        password: String,
        konfirmasi: String
    ): Boolean {
        var isValid = true

        // Cek nama tidak kosong
        if (nama.isEmpty()) {
            binding.tilNamaRegister.error = "Nama tidak boleh kosong"
            isValid = false
        } else {
            binding.tilNamaRegister.error = null
            binding.tilNamaRegister.isErrorEnabled = false
        }

        // Cek username minimal 4 karakter
        if (username.isEmpty()) {
            binding.tilUsernameRegister.error = "Username tidak boleh kosong"
            isValid = false
        } else if (username.length < 4) {
            binding.tilUsernameRegister.error = "Username minimal 4 karakter"
            isValid = false
        } else {
            binding.tilUsernameRegister.error = null
            binding.tilUsernameRegister.isErrorEnabled = false
        }

        // Cek password minimal 6 karakter
        if (password.isEmpty()) {
            binding.tilPasswordRegister.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            binding.tilPasswordRegister.error = "Password minimal 6 karakter"
            isValid = false
        } else {
            binding.tilPasswordRegister.error = null
            binding.tilPasswordRegister.isErrorEnabled = false
        }

        // Cek konfirmasi password harus sama dengan password
        if (konfirmasi.isEmpty()) {
            binding.tilKonfirmasiPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (konfirmasi != password) {
            binding.tilKonfirmasiPassword.error = "Password tidak cocok"
            isValid = false
        } else {
            binding.tilKonfirmasiPassword.error = null
            binding.tilKonfirmasiPassword.isErrorEnabled = false
        }

        return isValid
    }

    // Kembali ke halaman Login
    private fun setupTombolKeLogin() {
        binding.tvLoginDisini.setOnClickListener {
            finish() // Cukup finish() karena LoginActivity sudah ada di back stack
        }
    }
}