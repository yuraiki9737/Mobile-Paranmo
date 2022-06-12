package com.navigation.latihan.paranmo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    val success : Int,
    val status : Int,
    val message : String,
    val user : LoginAccount,
    )

data class LoginAccount(

    val id: String,
    val name: String,
    val email : String,
    val token: String,
    val isLogin: Boolean = false
    )