package com.navigation.latihan.paranmo.model

import android.util.Log
import androidx.lifecycle.*
import com.navigation.latihan.paranmo.api.RetrofitClient
import com.navigation.latihan.paranmo.data.Article
import com.navigation.latihan.paranmo.data.response.ArticleResponse
import com.navigation.latihan.paranmo.data.response.LoginAccount
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel (private val preferenceAkunParanmo: PreferenceAkunParanmo): ViewModel() {

    val articleList = MutableLiveData<ArrayList<Article>?>()
    fun setParanmo(id : String) {
        Log.d(this@HomeViewModel::class.java.simpleName, id)
        RetrofitClient().getApiParanmo().getArticle(id = id)
            .enqueue(object : retrofit2.Callback<ArticleResponse>{
                override fun onResponse(
                    call: Call<ArticleResponse>,
                    response: Response<ArticleResponse>,
                ) {
                   if(response.isSuccessful){
                       articleList.postValue(response.body()?.article)

                   }else{
                       articleList.postValue(null)
                   }
                }

                override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                    articleList.postValue(null)
                }

            })
    }


    fun getArticle(): MutableLiveData<ArrayList<Article>?>{
        return articleList
    }
    fun getUserParanmo(): LiveData<LoginAccount>{
        return preferenceAkunParanmo.getAkunParanmo().asLiveData()
    }

    fun logout(){
        viewModelScope.launch {
            preferenceAkunParanmo.logoutAccountParanmo()
        }
    }
}