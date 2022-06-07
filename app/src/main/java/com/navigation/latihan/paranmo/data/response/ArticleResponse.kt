package com.navigation.latihan.paranmo.data.response

import com.navigation.latihan.paranmo.data.Article

data class ArticleResponse (

    val success : Int,
    val status : Int,
    val message : String,
    val article : ArrayList<Article>
        )