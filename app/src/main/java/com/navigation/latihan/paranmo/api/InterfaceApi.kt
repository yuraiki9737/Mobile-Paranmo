package com.navigation.latihan.paranmo.api

import com.navigation.latihan.paranmo.data.LoginUser
import com.navigation.latihan.paranmo.data.RegisterUser
import com.navigation.latihan.paranmo.data.ResponseLogin
import com.navigation.latihan.paranmo.data.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InterfaceApi {

    @POST("login")
    fun loginAkunParanmo(
        @Body login : LoginUser
    ): Call<ResponseLogin>

    @POST("register")
    fun registerAkunParanmo(
        @Body register : RegisterUser
    ): Call<ResponseRegister>
}