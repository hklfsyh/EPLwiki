package com.example.eplwiki

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ClubDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        // Ambil data dari Intent
        val logo = intent.getIntExtra("club_logo", 0)
        val name = intent.getStringExtra("club_name")
        val slogan = intent.getStringExtra("club_slogan")
        val foundedYear = intent.getStringExtra("club_founded")
        val description = intent.getStringExtra("club_description")
        val stadiumName = intent.getStringExtra("club_stadium_name")
        val stadiumImage = intent.getIntExtra("club_stadium_image", 0)
        val coachName = intent.getStringExtra("club_coach_name")
        val coachImage = intent.getIntExtra("club_coach_image", 0)
        val playerNames = intent.getStringArrayExtra("club_player_names")
        val playerImages = intent.getIntArrayExtra("club_player_images")

        // Mengatur judul halaman pada ActionBar bawaan
        supportActionBar?.apply {
            title = "Club Detail" // Atur judul halaman
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol back
        }

        // Set data ke tampilan UI
        findViewById<ImageView>(R.id.club_detail_logo).setImageResource(logo)
        findViewById<TextView>(R.id.club_detail_name).text = name
        findViewById<TextView>(R.id.club_detail_slogan).text = slogan
        findViewById<TextView>(R.id.club_detail_founded_year).text = foundedYear
        findViewById<TextView>(R.id.club_detail_description).text = description
        findViewById<TextView>(R.id.club_detail_stadium).text = stadiumName
        findViewById<ImageView>(R.id.club_detail_stadium_image).setImageResource(stadiumImage)
        findViewById<TextView>(R.id.club_detail_coach_name).text = coachName
        findViewById<ImageView>(R.id.club_detail_coach_image).setImageResource(coachImage)

        // Set RecyclerView untuk pemain kunci
        val recyclerView = findViewById<RecyclerView>(R.id.club_detail_players_recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val players = mutableListOf<Player>()
        if (playerNames != null && playerImages != null) {
            for (i in playerNames.indices) {
                players.add(Player(playerNames[i], playerImages[i]))
            }
        }
        recyclerView.adapter = PlayerAdapter(players)

        // Tambahkan logika untuk tombol share
        val shareButton = findViewById<Button>(R.id.action_share)
        shareButton.setOnClickListener {
            val shareText = """
                Club Name: $name
                Founded Year: $foundedYear
                Slogan: $slogan
                Description: $description
            """.trimIndent()
            shareClubDetails(shareText)
        }
    }

    // Fungsi untuk berbagi data club
    private fun shareClubDetails(shareText: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share club details via"))
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
