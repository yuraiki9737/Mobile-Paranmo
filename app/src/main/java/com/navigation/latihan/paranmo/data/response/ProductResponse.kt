package com.navigation.latihan.paranmo.data.response

import com.navigation.latihan.paranmo.data.Product

data class ProductResponse (

    val success : Int,
    val status : Int,
    val message : String,
    val product : ArrayList<Product>
)
