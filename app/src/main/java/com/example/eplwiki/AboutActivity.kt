package com.example.eplwiki

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Mengatur judul halaman pada ActionBar bawaan
        supportActionBar?.apply {
            title = "About" // Atur judul halaman
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol back
        }
    }

    // Mengatasi tombol back
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Ketika tombol back ditekan
                onBackPressed() // Kembali ke halaman sebelumnya
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
