# 📱 Seminar Registration App

Aplikasi mobile Android untuk registrasi peserta seminar dengan antarmuka yang user-friendly.

## 📋 Fitur Utama

- ✅ Registrasi peserta seminar
- ✅ Form input dengan validasi
- ✅ Simpan data peserta
- ✅ Tampil hasil registrasi
- ✅ Interface yang responsif

## 🛠️ Teknologi yang Digunakan

- **Language:** Kotlin & Java
- **Platform:** Android Studio
- **Minimum SDK:** Android 5.0 (API 21)
- **Target SDK:** Android 12+ (API 31+)


## 🚀 Cara Menggunakan

### 1. Clone Repository
```bash
git clone https://github.com/username/SeminarRegistrationApp.git
cd SeminarRegistrationApp
```

### 2. Buka di Android Studio
- File → Open → Pilih folder project
- Tunggu gradle sync selesai

### 3. Jalankan Aplikasi
- Buka emulator atau sambungkan device Android
- Tekan `Shift + F10` atau klik tombol Run
- Aplikasi akan ter-install dan terbuka otomatis

## 📸 Screenshot & 🎥 Video Demo

Semua screenshot dan video demo tersedia di Google Drive:

👉 **[Buka Folder Google Drive](https://drive.google.com/drive/folders/1bVIU4K5PiksuTxkRfnQby5AeC55ujHNR)**

### Daftar File:
- `screenshot1.png` - Halaman Login
- `screenshot2.png` - Halaman Home
- `screenshot3.png` - Halaman Registrasi
- `screenshot4.png` - Halaman Hasil
- `demo.mp4` - Video Demo Aplikasi

## 📝 Fitur Halaman

### LoginActivity
- Input username & password
- Navigasi ke halaman utama

### HomeActivity
- Tampilan awal aplikasi
- Menu navigasi ke halaman lain

### FormActivity
- Form input data peserta (Nama, Email, No Telepon, dll)
- Validasi input form
- Tombol submit

### RegisterActivity
- Halaman registrasi user baru

### ResultActivity
- Menampilkan hasil registrasi
- Data yang sudah disimpan

## 🔧 Setup Awal (Development)

### Requirements
- Android Studio 4.2 atau lebih baru
- JDK 11+
- Android SDK (API 31+)

### Instalasi
```bash
# Clone project
git clone https://github.com/username/SeminarRegistrationApp.git

# Buka di Android Studio
cd SeminarRegistrationApp
```

## 📚 Library yang Digunakan

```gradle
dependencies {
    // Android Core
    implementation 'androidx.appcompat:appcompat:1.x.x'
    implementation 'androidx.constraintlayout:constraintlayout:2.x.x'
    
    // Testing
    testImplementation 'junit:junit:4.x'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.x'
}
```

## 🐛 Troubleshooting

### Error: ADB not found
```bash
# Set PATH atau gunakan full path
C:\Users\[YourUsername]\AppData\Local\Android\Sdk\platform-tools\adb kill-server
```

### Emulator sudah berjalan
```bash
adb kill-server
adb start-server
```

### Gradle sync gagal
- File → Invalidate Caches → Restart
- Atau: Build → Clean Project → Rebuild Project

## 👨‍💻 Author


- GitHub: [@username](https://github.com/ahmadagungmaulana)


**Made with ❤️ by Ahmad Agung Maulana**
