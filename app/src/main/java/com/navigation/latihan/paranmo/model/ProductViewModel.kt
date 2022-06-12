package com.navigation.latihan.paranmo.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.navigation.latihan.paranmo.api.RetrofitClient
import com.navigation.latihan.paranmo.data.LoginUser
import com.navigation.latihan.paranmo.data.Product
import com.navigation.latihan.paranmo.data.response.LoginAccount
import com.navigation.latihan.paranmo.data.response.ProductResponse
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel (private val preferenceAkunParanmo: PreferenceAkunParanmo): ViewModel() {

    val productList = MutableLiveData<ArrayList<Product>?>()

    fun setProduct(id : String){
        Log.d(this@ProductViewModel::class.java.simpleName, id)
        RetrofitClient().getApiParanmo().getProduct(id = id)
            .enqueue(object : Callback<ProductResponse>{
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>,
                ) {
                   if(response.isSuccessful){
                       productList.postValue(response.body()?.product)
                   } else {
                       productList.postValue(null)
                   }
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                   productList.postValue(null)
                }

            })
    }

    fun getProduct(): MutableLiveData<ArrayList<Product>?>{
        return productList
    }

    fun getUserParanmo(): LiveData<LoginAccount>{
        return preferenceAkunParanmo.getAkunParanmo().asLiveData()
    }
}