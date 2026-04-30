package com.example.seminarregistrationapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.seminarregistrationapp.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    // ViewBinding untuk mengakses semua view di activity_form.xml
    private lateinit var binding: ActivityFormBinding

    // Daftar 5 seminar yang di-hardcode sesuai spesifikasi tugas
    private val daftarSeminar = listOf(
        "Pilih Seminar...",
        "Seminar Teknologi AI 2024",
        "Seminar Kewirausahaan Digital",
        "Seminar Kesehatan Mental Mahasiswa",
        "Seminar Pemrograman Mobile",
        "Seminar Cyber Security"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        // Panggil semua fungsi setup saat Activity dibuat
        setupSpinner()
        setupValidasi()
        setupTombolSubmit()
    }

    // =============================================
    // FUNGSI 1: Setup Spinner (Dropdown Seminar)
    // =============================================
    private fun setupSpinner() {
        // ArrayAdapter menghubungkan list daftarSeminar ke AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            daftarSeminar
        )

        binding.spinnerSeminar.setAdapter(adapter)

        // Set teks default ke item pertama ("Pilih Seminar...")
        binding.spinnerSeminar.setText(daftarSeminar[0], false)

        // Tampilkan dropdown saat field diklik
        binding.spinnerSeminar.setOnClickListener {
            binding.spinnerSeminar.showDropDown()
        }
    }

    // =============================================
    // FUNGSI 2: Setup Validasi Real-time
    // TextWatcher memantau perubahan teks setiap
    // kali user mengetik, tanpa harus klik tombol
    // =============================================
    private fun setupValidasi() {

        // --- Validasi Nama ---
        // Nama tidak boleh kosong
        binding.etNama.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                // afterTextChanged dipanggil SETELAH teks berubah
                val nama = s.toString().trim()
                if (nama.isEmpty()) {
                    // Tampilkan pesan error di bawah field
                    binding.tilNama.error = "Nama tidak boleh kosong"
                } else {
                    // Hapus pesan error jika sudah diisi
                    binding.tilNama.error = null
                    binding.tilNama.isErrorEnabled = false
                }
            }
        })

        // --- Validasi Email ---
        // Email wajib mengandung karakter "@"
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()
                when {
                    email.isEmpty() -> {
                        binding.tilEmail.error = "Email tidak boleh kosong"
                    }
                    // Cek apakah email mengandung "@"
                    !email.contains("@") -> {
                        binding.tilEmail.error = "Email harus mengandung '@'"
                    }
                    else -> {
                        binding.tilEmail.error = null
                        binding.tilEmail.isErrorEnabled = false
                    }
                }
            }
        })

        // --- Validasi Nomor HP ---
        // Aturan: hanya angka, panjang 10-13 digit, diawali "08"
        binding.etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val phone = s.toString().trim()
                when {
                    phone.isEmpty() -> {
                        binding.tilPhone.error = "Nomor HP tidak boleh kosong"
                    }
                    // Cek apakah semua karakter adalah angka
                    !phone.all { it.isDigit() } -> {
                        binding.tilPhone.error = "Nomor HP hanya boleh berisi angka"
                    }
                    // Cek panjang nomor: minimal 10, maksimal 13 digit
                    phone.length !in 10..13 -> {
                        binding.tilPhone.error = "Nomor HP harus 10-13 digit"
                    }
                    // Cek apakah nomor diawali dengan "08"
                    !phone.startsWith("08") -> {
                        binding.tilPhone.error = "Nomor HP harus diawali '08'"
                    }
                    else -> {
                        binding.tilPhone.error = null
                        binding.tilPhone.isErrorEnabled = false
                    }
                }
            }
        })
    }

    // =============================================
    // FUNGSI 3: Validasi Semua Field Sebelum Submit
    // Mengembalikan true jika SEMUA valid
    // =============================================
    private fun validateAllFields(): Boolean {
        var isValid = true

        // Ambil semua nilai dari form
        val nama = binding.etNama.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val seminarDipilih = binding.spinnerSeminar.text.toString()
        val genderDipilih = binding.rgJenisKelamin.checkedRadioButtonId

        // --- Cek Nama ---
        if (nama.isEmpty()) {
            binding.tilNama.error = "Nama tidak boleh kosong"
            isValid = false
        }

        // --- Cek Email ---
        if (email.isEmpty() || !email.contains("@")) {
            binding.tilEmail.error = "Masukkan email yang valid"
            isValid = false
        }

        // --- Cek Nomor HP ---
        if (phone.isEmpty() || !phone.all { it.isDigit() } ||
            phone.length !in 10..13 || !phone.startsWith("08")) {
            binding.tilPhone.error = "Masukkan nomor HP yang valid (08xxxxxxxxxx)"
            isValid = false
        }

        // --- Cek Jenis Kelamin ---
        // checkedRadioButtonId bernilai -1 jika tidak ada yang dipilih
        if (genderDipilih == -1) {
            binding.tvGenderError.visibility = android.view.View.VISIBLE
            isValid = false
        } else {
            binding.tvGenderError.visibility = android.view.View.GONE
        }

        // --- Cek Seminar ---
        // Pastikan user tidak membiarkan pilihan di "Pilih Seminar..."
        if (seminarDipilih == daftarSeminar[0] || seminarDipilih.isEmpty()) {
            binding.tilSeminar.error = "Pilih seminar terlebih dahulu"
            isValid = false
        } else {
            binding.tilSeminar.error = null
            binding.tilSeminar.isErrorEnabled = false
        }

        // --- Cek Checkbox Persetujuan ---
        if (!binding.cbPersetujuan.isChecked) {
            binding.cbPersetujuan.error = "Anda harus menyetujui pernyataan ini"
            isValid = false
        }

        return isValid
    }

    // =============================================
    // FUNGSI 4: Setup Tombol Submit & AlertDialog
    // =============================================
    private fun setupTombolSubmit() {
        binding.btnSubmit.setOnClickListener {

            // Jalankan validasi lengkap saat tombol ditekan
            if (validateAllFields()) {

                // Jika semua valid, tampilkan AlertDialog konfirmasi
                tampilkanDialogKonfirmasi()
            }
        }
    }

    // =============================================
    // FUNGSI 5: AlertDialog Konfirmasi
    // Muncul setelah semua validasi lolos
    // =============================================
    private fun tampilkanDialogKonfirmasi() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Pendaftaran")
            .setMessage("Apakah data yang Anda isi sudah benar?")
            // Tombol "Ya" -> lanjut ke ResultActivity
            .setPositiveButton("Ya, Kirim") { _, _ ->
                kirimDataKeResult()
            }
            // Tombol "Tidak" -> tutup dialog, user bisa edit lagi
            .setNegativeButton("Cek Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false) // Dialog tidak hilang jika klik di luar
            .show()
    }

    // =============================================
    // FUNGSI 6: Kirim Data ke ResultActivity
    // Menggunakan Intent.putExtra untuk passing data
    // =============================================
    private fun kirimDataKeResult() {
        // Ambil label gender dari RadioButton yang dipilih
        val genderLabel = if (binding.rbLakilaki.isChecked) "Laki-laki" else "Perempuan"

        // Buat Intent dan masukkan semua data sebagai "Extra"
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("NAMA", binding.etNama.text.toString().trim())
            putExtra("EMAIL", binding.etEmail.text.toString().trim())
            putExtra("PHONE", binding.etPhone.text.toString().trim())
            putExtra("GENDER", genderLabel)
            putExtra("SEMINAR", binding.spinnerSeminar.text.toString())
        }

        startActivity(intent)
    }

    // Menangani tombol back di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}