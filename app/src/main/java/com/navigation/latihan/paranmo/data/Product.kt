package com.navigation.latihan.paranmo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
   val id : String,
   val image_product : String,
   val name_product : String,
   val price_product : String,
   val location : String,
   val star_product : String,
   val name_product_full : String,
   val date_product : String,
   val insight_product : String,
   val time_booking_product : String,
   val booking_product : String,
   val description_product : String,
   val star_score_product : String,
   val score_product : String,
   val time_product_insight : String,
   val name_person_product : String,
   val detail_insight : String,
   val name_outlite : String,
   val logo_outlite : String,


) : Parcelable