package com.example.eplwiki

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(val name: String, val image: Int): Parcelable
