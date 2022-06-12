package com.navigation.latihan.paranmo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParanNity(
    var photo : Int,
    var name : String?,
    var date : String?,
    var image : Int,
    var like : String?,
    var comment : String?,
    var share : String?,
):Parcelable
