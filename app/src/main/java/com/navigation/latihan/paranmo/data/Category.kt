package com.navigation.latihan.paranmo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var frameCat : Int,
    var viewImageCat : Int,
    var txtCat : String?,
):Parcelable
