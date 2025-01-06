package com.example.eplwiki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Club(
    val name: String,
    val logo: Int,
    val slogan: String,
    val foundedYear: String,
    val description: String,
    val stadiumName: String,
    val stadiumImage: Int,
    val coachName: String,
    val coachImage: Int,
    val playerNames: Array<String>,
    val playerImages: IntArray
): Parcelable

