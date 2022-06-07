package com.navigation.latihan.paranmo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (

    val id : String,
    val name : String,
    val plant_name : String,
    val latin_name : String,
    val description : String,
    val photo_url : String,
    val createdAt: String
): Parcelable
