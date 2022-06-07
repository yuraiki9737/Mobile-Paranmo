package com.navigation.latihan.paranmo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discount (
   var frameDis : Int,
   var viewPay : Int,
   var txtDis : String?,
   var txtDes : String?,
   var txtPer : String?,
   var txtPerStyle : Int,
   var disProd : Int,
   var viewImage : Int


) : Parcelable