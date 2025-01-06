package com.example.eplwiki

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ClubAdapter(private val clubList: List<Club>, private val onItemClick: (Club) -> Unit) :
    RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clubLogo: ImageView = itemView.findViewById(R.id.club_logo)
        val clubName: TextView = itemView.findViewById(R.id.club_name)
        val clubFounded: TextView = itemView.findViewById(R.id.club_founded)
        val clubSlogan: TextView = itemView.findViewById(R.id.club_slogan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_club, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = clubList[position]

        // Menggunakan Glide untuk memuat logo klub dari resource ID
        Glide.with(holder.itemView.context)
            .load(club.logo) // club.logo harus berupa resource ID yang valid
            .placeholder(R.drawable.logoligainggris) // Placeholder saat gambar dimuat
            .error(R.drawable.londonstadium) // Jika terjadi error saat memuat gambar
            .into(holder.clubLogo) // Memasukkan gambar ke ImageView

        holder.clubName.text = club.name
        holder.clubFounded.text = "${club.foundedYear}"
        holder.clubSlogan.text = club.slogan

        holder.itemView.setOnClickListener {
            onItemClick(club)
        }
    }

    override fun getItemCount() = clubList.size
}
