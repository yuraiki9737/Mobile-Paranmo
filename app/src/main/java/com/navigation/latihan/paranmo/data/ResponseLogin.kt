package com.navigation.latihan.paranmo.data

data class ResponseLogin(
    val user : LoginAccount,
    val success : Int,
    val message : String,

)

data class LoginAccount(
    val id: String,
    val name: String,
    val token: String,
    val isLogin: Boolean,

    )