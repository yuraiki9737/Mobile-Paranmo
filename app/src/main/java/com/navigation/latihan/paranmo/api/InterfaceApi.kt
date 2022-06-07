package com.navigation.latihan.paranmo.api

import com.navigation.latihan.paranmo.data.LoginUser
import com.navigation.latihan.paranmo.data.RegisterUser
import com.navigation.latihan.paranmo.data.response.ArticleResponse
import com.navigation.latihan.paranmo.data.response.ProductResponse
import com.navigation.latihan.paranmo.data.response.ResponseLogin
import com.navigation.latihan.paranmo.data.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface InterfaceApi {

    @POST("login.php")
    fun loginAkunParanmo(
        @Body login : LoginUser
    ): Call<ResponseLogin>

    @POST("register.php")
    fun registerAkunParanmo(
        @Body register : RegisterUser
    ): Call<ResponseRegister>

    @GET("getStories.php")
    fun getArticle(@Header("Authorization") id : String): Call<ArticleResponse>

    @GET("getProduct.php")
    fun getProduct(@Header("Authorization") id: String) : Call<ProductResponse>
}