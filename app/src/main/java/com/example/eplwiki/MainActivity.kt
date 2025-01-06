package com.example.eplwiki

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var clubAdapter: ClubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView_clubs)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Ambil data dari strings.xml
        val clubNames = resources.getStringArray(R.array.premier_league_club_names)
        val clubLogos = resources.obtainTypedArray(R.array.premier_league_club_logos)
        val clubSlogans = resources.getStringArray(R.array.premier_league_club_slogans)
        val clubFoundedYears = resources.getStringArray(R.array.premier_league_founding_years)
        val clubDescriptions = resources.getStringArray(R.array.premier_league_club_descriptions)
        val clubStadiumNames = resources.getStringArray(R.array.premier_league_club_stadiums)
        val clubStadiumImages =
            resources.obtainTypedArray(R.array.premier_league_club_stadium_images)
        val clubCoaches = resources.getStringArray(R.array.premier_league_club_managers)
        val coachImages =
            resources.obtainTypedArray(R.array.premier_league_club_coaches)  // Ambil coach images

        // Data pemain kunci bisa berbeda-beda tergantung klub
        val clubPlayerNames = arrayOf(
            resources.getStringArray(R.array.arsenal_key_players),
            resources.getStringArray(R.array.astonvilla_key_players),
            resources.getStringArray(R.array.bournemouth_key_players),
            resources.getStringArray(R.array.brentford_key_players),
            resources.getStringArray(R.array.brighton_key_players),
            resources.getStringArray(R.array.chelsea_key_players),
            resources.getStringArray(R.array.crystalpalace_key_players),
            resources.getStringArray(R.array.everton_key_players),
            resources.getStringArray(R.array.fulham_key_players),
            resources.getStringArray(R.array.ipswich_key_players),
            resources.getStringArray(R.array.leicester_key_players),
            resources.getStringArray(R.array.liverpool_key_players),
            resources.getStringArray(R.array.manchestercity_key_players),
            resources.getStringArray(R.array.manchester_united_key_players),
            resources.getStringArray(R.array.newcastle_key_players),
            resources.getStringArray(R.array.nottingham_key_players),
            resources.getStringArray(R.array.southampton_key_players),
            resources.getStringArray(R.array.tottenham_key_players),
            resources.getStringArray(R.array.westham_key_players),
            resources.getStringArray(R.array.wolves_key_players)
        )

        val clubPlayerImages = arrayOf(
            resources.obtainTypedArray(R.array.arsenal_key_players_images),
            resources.obtainTypedArray(R.array.astonvilla_key_players_images),
            resources.obtainTypedArray(R.array.bournemouth_key_players_images),
            resources.obtainTypedArray(R.array.brentford_key_players_images),
            resources.obtainTypedArray(R.array.brighton_key_players_images),
            resources.obtainTypedArray(R.array.chelsea_key_players_images),
            resources.obtainTypedArray(R.array.crystalpalace_key_players_images),
            resources.obtainTypedArray(R.array.everton_key_players_images),
            resources.obtainTypedArray(R.array.fulham_key_players_images),
            resources.obtainTypedArray(R.array.ipswich_key_players_images),
            resources.obtainTypedArray(R.array.leicester_key_players_images),
            resources.obtainTypedArray(R.array.liverpool_key_players_images),
            resources.obtainTypedArray(R.array.manchestercity_key_players_images),
            resources.obtainTypedArray(R.array.manchester_united_key_players_images),
            resources.obtainTypedArray(R.array.newcastle_key_players_images),
            resources.obtainTypedArray(R.array.nottingham_key_players_images),
            resources.obtainTypedArray(R.array.southampton_key_players_images),
            resources.obtainTypedArray(R.array.tottenham_key_players_images),
            resources.obtainTypedArray(R.array.westham_key_players_images),
            resources.obtainTypedArray(R.array.wolves_key_players_images)
        )

        val clubs = mutableListOf<Club>()

        for (i in clubNames.indices) {
            val club = Club(
                name = clubNames[i],
                logo = clubLogos.getResourceId(i, -1),
                slogan = clubSlogans[i],
                foundedYear = clubFoundedYears[i],
                description = clubDescriptions[i],
                stadiumName = clubStadiumNames[i],
                stadiumImage = clubStadiumImages.getResourceId(i, -1),
                coachName = clubCoaches[i],
                coachImage = coachImages.getResourceId(i, -1),  // Tambahkan ini
                playerNames = clubPlayerNames[i],
                playerImages = clubPlayerImages[i].let { array ->
                    IntArray(array.length()) { index -> array.getResourceId(index, -1) }
                }
            )
            clubs.add(club)
        }

        // Recycle resources setelah digunakan
        clubLogos.recycle()
        clubStadiumImages.recycle()
        coachImages.recycle()  // Jangan lupa untuk recycle coach images
        clubPlayerImages.forEach { it.recycle() }

        // Set adapter ke RecyclerView
        clubAdapter = ClubAdapter(clubs) { club ->
            // Intent ke halaman detail klub
            val intent = Intent(this, ClubDetailActivity::class.java).apply {
                putExtra("club_name", club.name)
                putExtra("club_logo", club.logo)
                putExtra("club_slogan", club.slogan)
                putExtra("club_founded", club.foundedYear)
                putExtra("club_description", club.description)
                putExtra("club_stadium_name", club.stadiumName)
                putExtra("club_stadium_image", club.stadiumImage)
                putExtra("club_coach_name", club.coachName)
                putExtra("club_coach_image", club.coachImage)  // Kirim gambar coach
                putExtra("club_player_names", club.playerNames)
                putExtra("club_player_images", club.playerImages)
            }
            startActivity(intent)
        }
        recyclerView.adapter = clubAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                // Navigasi ke halaman About
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
