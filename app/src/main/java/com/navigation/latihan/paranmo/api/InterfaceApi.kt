package com.navigation.latihan.paranmo.api


import com.navigation.latihan.paranmo.data.LoginUser
import com.navigation.latihan.paranmo.data.RegisterUser
import com.navigation.latihan.paranmo.data.StoriesByName
import com.navigation.latihan.paranmo.data.response.ArticleResponse
import com.navigation.latihan.paranmo.data.response.ProductResponse
import com.navigation.latihan.paranmo.data.response.ResponseLogin
import com.navigation.latihan.paranmo.data.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface InterfaceApi {

    @POST("login.php")
    fun loginAkunParanmo(
     @Body loginUser: LoginUser
    ): Call<ResponseLogin>

    @POST("register.php")
    fun registerAkunParanmo(
        @Body registerUser: RegisterUser): Call<ResponseRegister>

    @GET("getArticles.php")
    fun getArticle(@Header("Authorization") id : String): Call<ArticleResponse>

    @GET("getProduct.php")
    fun getProduct(@Header("Authorization") id: String) : Call<ProductResponse>

    @POST("getArticleByName.php")
    fun postStoriesByName(
        @Body storiesByName : StoriesByName
    ) : Call<ArticleResponse>

}