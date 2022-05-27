package com.navigation.latihan.paranmo.data

data class ResponseLogin(
    val user : LoginAkun,
    val error : Int,
    val message : String,
)

data class LoginAkun(
    val id : Int,
    val name : String,
    val email : String,
    val isLogin : Boolean,

)